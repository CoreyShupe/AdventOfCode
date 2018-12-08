package com.github.coreyshupe.adventofcode.y2015

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(15, 3, ResourceType.Full) {
        println(findHouseCount(it)) // part 1
        println(findHouseCountWithRobot(it)) // part 2
    }
}

fun findHouseCount(input: String): Int {
    var pointer = Pair(0, 0)
    val set = mutableSetOf(pointer)
    input.forEach {
        pointer = applyPointer(pointer, it)
        set.add(pointer)
    }
    return set.size
}

fun findHouseCountWithRobot(input: String): Int {
    var pointer = Pair(0, 0)
    var robotPointer = Pair(0, 0)
    var which = true
    val set = mutableSetOf(pointer)
    input.forEach {
        if (which) {
            pointer = applyPointer(pointer, it)
            set.add(pointer)
        } else {
            robotPointer = applyPointer(robotPointer, it)
            set.add(robotPointer)
        }
        which = !which
    }
    return set.size
}

private fun applyPointer(current: Pair<Int, Int>, chr: Char) = when (chr) {
    '>' -> Pair(current.first + 1, current.second)
    '<' -> Pair(current.first - 1, current.second)
    '^' -> Pair(current.first, current.second + 1)
    'v' -> Pair(current.first, current.second - 1)
    else -> throw IllegalArgumentException("$chr not a legal direction.")
}