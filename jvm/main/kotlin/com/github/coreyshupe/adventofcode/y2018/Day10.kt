package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 10, ResourceType.Lined) {
        val answer = findMessage(it)
        println("In ${answer.second}s we got the message:") // this requires brute force shit
        println(answer.first)
    }
}

fun findMessage(input: List<String>): Pair<String, Int> {
    val positions = input.map {
        val split = it.split(" v")
        val positionPieces = split[0].substring(10).dropLast(1).split(", ").map { it.trim().toInt() }
        val velocityPieces = split[1].substring(9).dropLast(1).split(", ").map { it.trim().toInt() }
        Position(positionPieces[0] to positionPieces[1], velocityPieces[0] to velocityPieces[1])
    }
    var time = 0
    var last: Triple<Set<Pair<Int, Int>>, Int, Pair<Int, Int>>? = null
    while (true) {
        val fixedPos = positions.map { it getCurrentPoint time }.toSet()
        time++
        val maxY = fixedPos.maxBy { it.second }!!.second
        val minY = fixedPos.minBy { it.second }!!.second
        if (maxY - minY > last?.second ?: Int.MAX_VALUE) {
            val maxX = last!!.first.maxBy { it.first }!!.first
            val minX = last.first.minBy { it.first }!!.first
            return Pair(normalize(last.first, minX to maxX, last.third.first to last.third.second), time - 2)
        }
        last = Triple(fixedPos, maxY - minY, Pair(minY, maxY))
    }
}

private fun normalize(positions: Set<Pair<Int, Int>>, xBounds: Pair<Int, Int>, yBounds: Pair<Int, Int>) = buildString {
    for (y in yBounds.first..yBounds.second) {
        for (x in xBounds.first..xBounds.second) {
            append((if (positions.contains(x to y)) '#' else '.'))
        }
        append('\n')
    }
}

private data class Position(val initialPos: Pair<Int, Int>, val velocity: Pair<Int, Int>) {
    infix fun getCurrentPoint(time: Int) =
        Pair(initialPos.first + (velocity.first * time), initialPos.second + (velocity.second * time))
}