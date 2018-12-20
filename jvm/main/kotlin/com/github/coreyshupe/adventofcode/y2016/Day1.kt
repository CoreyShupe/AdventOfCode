package com.github.coreyshupe.adventofcode.y2016

import com.github.coreyshupe.adventofcode.Direction
import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import kotlin.math.abs

fun main(args: Array<String>) {
    input(16, 1, ResourceType.CommaSplit) {
        println(findDistance(it)) // part 1
        println(findLocationVisitedTwice(it)) // part 2
    }
}

fun findDistance(input: List<String>): Int {
    var pointer = Pair(0, 0)
    var direction: Direction = Direction.NORTH
    input.map { it.trim() }.forEach {
        val dir = it[0]
        val length = it.substring(1)
        direction = direction.move(dir == 'L')
        pointer = direction.apply(pointer, length.toInt())
    }
    return abs(pointer.first) + abs(pointer.second)
}

fun findLocationVisitedTwice(input: List<String>): Int {
    val set = mutableSetOf<Pair<Int, Int>>()
    var pointer = Pair(0, 0)
    var direction: Direction = Direction.NORTH
    input.map { it.trim() }.forEach {
        val dir = it[0]
        val length = it.substring(1)
        direction = direction.move(dir == 'L')
        for (x in 1..length.toInt()) {
            pointer = direction.apply(pointer, 1)
            if (!set.add(pointer)) return abs(pointer.first) + abs(pointer.second)
        }
    }
    throw IllegalArgumentException("Invalid input.")
}