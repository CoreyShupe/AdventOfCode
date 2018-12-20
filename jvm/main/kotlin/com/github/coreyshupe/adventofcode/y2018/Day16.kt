package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 16, ResourceType.Lined) {
        runInstructionSet(it).let(::println) // part 1, part 2
    }
}

private val ops = mapOf<String, (register: Array<Int>, a: Int, b: Int) -> Int>(
    // mathematical functions
    "addr" to { register, a, b -> register[a] + register[b] }, "addi" to { register, a, b -> register[a] + b },
    "mulr" to { register, a, b -> register[a] * register[b] }, "muli" to { register, a, b -> register[a] * b },
    "banr" to { register, a, b -> register[a] and register[b] }, "bani" to { register, a, b -> register[a] and b },
    "borr" to { register, a, b -> register[a] or register[b] }, "bori" to { register, a, b -> register[a] or b },
    "setr" to { register, a, _ -> register[a] }, "seti" to { _, a, _ -> a },
    // comparison gt functions
    "gtir" to { register, a, b -> if (a > register[b]) 1 else 0 },
    "gtri" to { register, a, b -> if (register[a] > b) 1 else 0 },
    "gtrr" to { register, a, b -> if (register[a] > register[b]) 1 else 0 },
    // comparison eq functions
    "eqir" to { register, a, b -> if (a == register[b]) 1 else 0 },
    "eqri" to { register, a, b -> if (register[a] == b) 1 else 0 },
    "eqrr" to { register, a, b -> if (register[a] == register[b]) 1 else 0 }
)

private fun fixTestable(data: String) = data.split(" [")[1].dropLast(1).split(", ").map(String::toInt).toTypedArray()
private fun fixIntSet(data: String) = data.split(' ').map(String::toInt).toTypedArray()
private fun testOps(info: String, before: String, after: String): Set<String> {
    val fixedInfo = fixIntSet(info)
    val fixedBefore = fixTestable(before)
    val fixedAfter = fixTestable(after)
    val a = fixedInfo[1]
    val b = fixedInfo[2]
    val c = fixedInfo[3]
    return ops.filter { it.value(fixedBefore, a, b) == fixedAfter[c] }.keys
}

fun runInstructionSet(input: List<String>): Pair<Int, Int> {
    var start = 0
    var count = 0
    val map = mutableMapOf<Int, MutableSet<String>>()
    val banned = mutableSetOf<String>()
    fun removeOp(code: String) {
        banned.add(code)
        map.values.filter { it.size > 1 }.forEach {
            it.remove(code)
            if (it.size == 1) removeOp(it.first())
        }
    }

    fun addInst(op: Int, possibleOps: MutableSet<String>) {
        if (!map.containsKey(op)) {
            map[op] = possibleOps
            if (possibleOps.size == 1) {
                removeOp(possibleOps.first())
            }
        } else {
            val next = map[op]!!
            if (next.size == 1) return
            next.removeAll { !possibleOps.contains(it) }
            if (next.size == 1) {
                removeOp(next.first())
            }
        }
    }

    for (i in (0.until(input.size) step 4)) {
        if (!input[i].startsWith("Before")) {
            start = i
            break
        }
        val op = input[i + 1].split(' ')[0].toInt()
        val instructionsRaw = testOps(input[i + 1], input[i], input[i + 2])
        if (instructionsRaw.size > 2) count++
        val instructions = instructionsRaw.filter { !banned.contains(it) }.toMutableSet()
        addInst(op, instructions)
    }
    val functions = Array(16) { ops[map[it]!!.first()]!! }
    val register = arrayOf(0, 0, 0, 0)

    (start.until(input.size)).map(input::get).map(::fixIntSet)
        .forEach { register[it[3]] = functions[it[0]](register, it[1], it[2]) }
    return count to register[0]
}