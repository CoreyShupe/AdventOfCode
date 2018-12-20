package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import kotlin.math.sqrt

fun main(args: Array<String>) {
    input(18, 19, ResourceType.Lined) {
        findInstructionHalt(it, 0).let(::println) // part 1
        findInstructionHalt(it, 1).let(::println) // part 2
    }
}

fun findInstructionHalt(input: List<String>, initReg0: Int = 0): Int {
    val register = arrayOf(initReg0, 0, 0, 0, 0, 0)
    val ip = input[0].split(' ')[1].toInt()
    val instructions = input.drop(1).map {
        val split = it.split(' ')
        ops[split[0]]!! to split.drop(1).map(String::toInt)
    }
    var buffer = instructions.size
    while (--buffer > 0 && register[ip] < instructions.size && register[ip] >= 0) {
        val instructionPair = instructions[register[ip]]
        val values = instructionPair.second
        instructionPair.first(register, values[0], values[1], values[2])
        register[ip]++
    }
    return findSumOfDivisors(register.max()!!)
}

fun findSumOfDivisors(num: Int) = (1..sqrt(num.toDouble()).toInt()).filter { num % it == 0 }.sumBy { it + (num / it) }

private val ops = mapOf<String, (register: Array<Int>, a: Int, b: Int, c: Int) -> Unit>(
    // mathematical functions
    "addr" to { register, a, b, c -> register[c] = register[a] + register[b] },
    "addi" to { register, a, b, c -> register[c] = register[a] + b },
    "mulr" to { register, a, b, c -> register[c] = register[a] * register[b] },
    "muli" to { register, a, b, c -> register[c] = register[a] * b },
    "banr" to { register, a, b, c -> register[c] = register[a] and register[b] },
    "bani" to { register, a, b, c -> register[c] = register[a] and b },
    "borr" to { register, a, b, c -> register[c] = register[a] or register[b] },
    "bori" to { register, a, b, c -> register[c] = register[a] or b },
    "setr" to { register, a, _, c -> register[c] = register[a] },
    "seti" to { register, a, _, c -> register[c] = a },
    // comparison gt functions
    "gtir" to { register, a, b, c -> register[c] = if (a > register[b]) 1 else 0 },
    "gtri" to { register, a, b, c -> register[c] = if (register[a] > b) 1 else 0 },
    "gtrr" to { register, a, b, c -> register[c] = if (register[a] > register[b]) 1 else 0 },
    // comparison eq functions
    "eqir" to { register, a, b, c -> register[c] = if (a == register[b]) 1 else 0 },
    "eqri" to { register, a, b, c -> register[c] = if (register[a] == b) 1 else 0 },
    "eqrr" to { register, a, b, c -> register[c] = if (register[a] == register[b]) 1 else 0 }
)