package com.github.coreyshupe.adventofcode.y2017

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

fun checkSpiralMemory(target: Int): Int {
    val side = getSideLength(target)
    val toCenter = (side - 1) / 2
    val mid = {
        val high = side * side
        val offset = ((side - 1) / 2.0).toInt()
        (0..3).map {
            high - (offset + (it * side.dec()))
        }
    }
    return toCenter + mid.invoke().map { abs(target - it) }.min()!!
}

sealed class Direction(private val func: (x: Int, y: Int) -> Pair<Int, Int>) {
    fun move(pair: Pair<Int, Int>) = func.invoke(pair.first, pair.second)
    abstract val next: Direction
}

object Up : Direction({ x, y -> Pair(x, y + 1) }) {
    override val next: Direction get() = Left
}

object Down : Direction({ x, y -> Pair(x, y - 1) }) {
    override val next: Direction get() = Right
}

object Left : Direction({ x, y -> Pair(x - 1, y) }) {
    override val next: Direction get() = Down
}

object Right : Direction({ x, y -> Pair(x + 1, y) }) {
    override val next: Direction get() = Up
}

fun findValueWrit(target: Int): Int {
    val side = getSideLength(target)
    var index = Pair((side / 2), (side / 2))
    val list = (0 until side).map { IntArray(side) }.apply {
        this[index.first][index.second] = 1
    }
    var direction: Direction = Right
    fun getItem(x: Int, y: Int): Int? = if (x in (0 until side) && y in (0 until side)) list[x][y] else null
    fun getItem(pair: Pair<Int, Int>): Int? = getItem(pair.first, pair.second)
    fun calculateNeighbors() =
        (index.first - 1..index.first + 1).map { x -> (index.second - 1..index.second + 1).map { y -> getItem(x, y) } }
            .flatten().filterNotNull().sum()
    return generateSequence(1) {
        index = direction.move(index)
        list[index.first][index.second] = calculateNeighbors()
        direction = if (getItem(direction.next.move(index)) == 0) direction.next else direction
        getItem(index)
    }.first { it > target }
}

fun getSideLength(target: Int) = ceil(sqrt(target.toDouble())).toInt().let { if (it % 2 != 0) it else it + 1 }