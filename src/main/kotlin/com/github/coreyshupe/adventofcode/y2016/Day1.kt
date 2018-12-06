package com.github.coreyshupe.adventofcode.y2016

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import kotlin.math.abs

fun main(args: Array<String>) {
    "/2016/day1_input.txt".asResource(ResourceType.CommaSplit) {
        println(findDistance(it))
        println(findLocationVisitedTwice(it))
    }
}

fun findDistance(input: List<String>): Int {
    var pointer = Pair(0, 0)
    var direction: Direction = North
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
    var direction: Direction = North
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

private abstract class Direction {
    abstract fun apply(pointer: Pair<Int, Int>, len: Int): Pair<Int, Int>
    abstract fun move(left: Boolean): Direction
}

private object East : Direction() {
    override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first + len, pointer.second)
    override fun move(left: Boolean) = if (left) North else South
}

private object West : Direction() {
    override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first - len, pointer.second)
    override fun move(left: Boolean) = if (left) South else North
}

private object North : Direction() {
    override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first, pointer.second + len)
    override fun move(left: Boolean) = if (left) West else East
}

private object South : Direction() {
    override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first, pointer.second - len)
    override fun move(left: Boolean) = if (left) East else West
}