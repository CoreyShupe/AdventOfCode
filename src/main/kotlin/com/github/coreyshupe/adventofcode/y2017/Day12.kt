package com.github.coreyshupe.adventofcode.y2017

import java.util.*

fun findAllInProgram0(input: String): Int {
    return findAllInProgram(input.split('\n').map { parseGroup(it) }.toMap().toMutableMap(), 0).size
}

fun countGroups(input: String): Int {
    val map = input.split('\n').map { parseGroup(it) }.toMap().toMutableMap()
    var count = 0
    while (!map.isEmpty()) {
        findAllInProgram(map, map.keys.first())
        count++
    }
    return count
}

fun findAllInProgram(map: MutableMap<Int, List<Int>>, initial: Int): Set<Int> {
    val seen = mutableSetOf(initial)
    val stackState = Stack<List<Int>>()
    stackState.push(map[initial])
    map.remove(initial)
    while (!stackState.isEmpty()) {
        stackState.pop().filter { !seen.contains(it) }.forEach {
            seen.add(it)
            stackState.push(map[it])
            map.remove(it)
        }
    }
    return seen
}

fun parseGroup(line: String): Pair<Int, List<Int>> {
    val split = line.replace(",", "").split(' ')
    return Pair(split[0].toInt(), split.subList(2, split.size).map { it.toInt() })
}