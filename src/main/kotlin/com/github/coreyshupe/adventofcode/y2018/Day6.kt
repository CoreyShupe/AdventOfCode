package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import kotlin.math.abs

fun main(args: Array<String>) {
    "/2018/day6_input.txt".asResource(ResourceType.Lined) {
        println(findLargestFiniteRegion(it)) // part 1
        println(findCentralRegion(it, 10000)) // part 2
    }
}

fun findLargestFiniteRegion(input: List<String>) = genInfo(input).let { info ->
    val points = info.first
    val array = Array(points.size) { 0 }
    for (x in 0.until(info.second)) for (y in 0.until(info.third)) {
        val real = points.map { it.distance(x to y) to it }.let {
            it.filter { x -> x.first == it.minBy { z -> z.first }!!.first }
        }
        if (real.size != 1) continue
        val value = real.first().second.third
        if (array[value] == -1) continue
        array[value] =
                if ((y == 0 || y == info.third - 1) || (x == 0 || x == info.second - 1)) -1 else array[value] + 1
    }
    array.max()!!
}

fun findCentralRegion(input: List<String>, constraint: Int) = genInfo(input).let { info ->
    val points = info.first
    (0..info.second).sumBy { x ->
        (0..info.third).count { y ->
            points.map { it.distance(x to y) }.sum() < constraint
        }
    }
}

private fun genInfo(input: List<String>) = spawnPoints(input).let {
    Triple(
        it,
        it.maxBy { x -> x.first }!!.first + 1,
        it.maxBy { x -> x.second }!!.second + 1
    )
}

private fun spawnPoints(input: List<String>) =
    input.mapIndexed { i, x -> x.split(", ").map { it.toInt() }.let { Triple(it[0], it[1], i) } }

private fun Triple<Int, Int, Int>.distance(other: Pair<Int, Int>) =
    abs(first - other.first) + abs(second - other.second)