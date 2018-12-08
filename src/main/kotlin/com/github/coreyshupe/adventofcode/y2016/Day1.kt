package com.github.coreyshupe.adventofcode.y2016

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

private enum class Direction {
    EAST {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first + len, pointer.second)
        override fun move(left: Boolean) = if (left) NORTH else SOUTH
    },
    WEST {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first - len, pointer.second)
        override fun move(left: Boolean) = if (left) SOUTH else NORTH
    },
    NORTH {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first, pointer.second + len)
        override fun move(left: Boolean) = if (left) WEST else EAST
    },
    SOUTH {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first, pointer.second - len)
        override fun move(left: Boolean) = if (left) EAST else WEST
    };

    abstract fun apply(pointer: Pair<Int, Int>, len: Int): Pair<Int, Int>
    abstract fun move(left: Boolean): Direction
}