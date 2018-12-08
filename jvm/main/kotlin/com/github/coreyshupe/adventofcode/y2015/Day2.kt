package com.github.coreyshupe.adventofcode.y2015

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import kotlin.math.min

fun main(args: Array<String>) {
    input(15, 2, ResourceType.Lined) {
        println(findNeededWrappingPaper(it)) // part 1
        println(findNeededRibbon(it)) // part 2
    }
}

fun findNeededWrappingPaper(input: List<String>) = input.sumBy { calculatePaper(it) }

fun findNeededRibbon(input: List<String>) = input.sumBy { calculateRibbon(it) }

private fun calculatePaper(input: String): Int {
    val dimensions = parseDims(input)
    val s1 = dimensions[0] * dimensions[1]
    val s2 = dimensions[0] * dimensions[2]
    val s3 = dimensions[2] * dimensions[1]
    return (2 * (s1 + s2 + s3)) + min(s1, min(s2, s3))
}

private fun calculateRibbon(input: String): Int {
    val dimensions = parseDims(input)
    val s1 = (2 * dimensions[0]) + (2 * dimensions[1])
    val s2 = (2 * dimensions[1]) + (2 * dimensions[2])
    val s3 = (2 * dimensions[2]) + (2 * dimensions[0])
    return min(s1, min(s2, s3)) + (dimensions[0] * dimensions[1] * dimensions[2])
}

private fun parseDims(input: String) = input.split('x').map { it.toInt() }