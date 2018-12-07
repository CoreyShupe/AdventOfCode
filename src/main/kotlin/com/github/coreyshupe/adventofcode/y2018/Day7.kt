package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import kotlin.math.max

fun main(args: Array<String>) {
    "/2018/day7_input.txt".asResource(ResourceType.Lined) {
        println(findInstructionOrder(it))
        println(findInstructionTime(it, 5, 60))
    }
}

fun findInstructionOrder(input: List<String>) = buildString {
    val instructions = generateInstructions(input)
    val todo = sortedSetOf<Char>()
    instructions.filter(Instruction::available).forEach {
        instructions.remove(it)
        todo.add(it.id)
    }
    while (!todo.isEmpty()) {
        val pop = todo.pollFirst()
        append(pop)
        val toRemove = mutableSetOf<Instruction>()
        instructions.forEach {
            it.removeRequirement(pop)
            if (it.available) {
                toRemove.add(it)
                todo.add(it.id)
            }
        }
        toRemove.forEach { instructions.remove(it) }
    }
}

fun findInstructionTime(input: List<String>, workerCount: Int, adder: Int): Int {
    val instructions = generateInstructions(input)
    val todo = sortedSetOf<Char>()
    instructions.filter(Instruction::available).forEach {
        instructions.remove(it)
        todo.add(it.id)
    }
    val workers = MutableList(workerCount) { 0 to '-' }
    for (x in 0.until(workerCount)) {
        if (!todo.isEmpty()) {
            val first = todo.pollFirst()
            workers[x] = str.indexOf(first) + adder to first
        } else break
    }
    var time = 0
    while (workers.filter { it == 0 to '-' }.size != workerCount) {
        val min = workers.minBy { if (it.first == 0) Int.MAX_VALUE else it.first }!!
        time += min.first
        workers.replaceAll { max(0, it.first - min.first) to it.second }
        val workerObs = mutableSetOf<Int>()
        workers.withIndex().filter { it.value.first == 0 && it.value.second != '-' }.forEach {
            val removable = mutableSetOf<Instruction>()
            instructions.forEach { ins ->
                ins.removeRequirement(it.value.second)
                if (ins.available) {
                    removable.add(ins)
                    todo.add(ins.id)
                }
            }
            removable.forEach { ins -> instructions.remove(ins) }
            workerObs.add(it.index)
        }
        workerObs.forEach { workers[it] = 0 to '-' }
        workers.withIndex().filter { it.value == 0 to '-' }.forEach {
            if (!todo.isEmpty()) {
                val first = todo.pollFirst()
                workers[it.index] = str.indexOf(first) + adder to first
            } else return@forEach
        }
    }
    return time
}

private fun generateInstructions(input: List<String>): MutableSet<Instruction> {
    val map = mutableMapOf<Char, Instruction>()
    input.forEach {
        val bId = it[5]
        val eId = it[36]
        if (!map.containsKey(bId)) map[bId] = Instruction(bId)
        if (!map.containsKey(eId)) map[eId] = Instruction(eId)
        map[eId]!!.addRequirement(bId)
    }
    return map.values.toMutableSet()
}

private const val str = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ"

private class Instruction(val id: Char) {
    private val requirements = mutableSetOf<Char>()
    val available get() = requirements.size == 0

    fun addRequirement(c: Char) = requirements.add(c)
    fun removeRequirement(c: Char) = requirements.remove(c)
}