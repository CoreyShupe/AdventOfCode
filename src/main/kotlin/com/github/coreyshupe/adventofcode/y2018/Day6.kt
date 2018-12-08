package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import kotlin.math.abs

fun main(args: Array<String>) {
    "/2018/day6_input".asResource(ResourceType.Lined) {
        println(findLargestFiniteRegion(it)) // part 1
        println(findCentralRegion(it, 10000)) // part 2
    }
}

fun findLargestFiniteRegion(input: List<String>) = genInfo(input).let { info ->
    val points = info.first
    val array = Array(points.size) { 0 }
    for (x in 0.until(info.second)) for (y in 0.until(info.third)) {
        val pair = x to y
        val real = points.map { it.distance(pair) to it }.let {
            val min = it.minBy { z -> z.first }!!.first
            it.filter { x -> x.first == min }
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
    var found = false
    var terminated = false
    (0..info.second).sumBy { x ->
        if (terminated) return@sumBy 0
        var foundRow = false
        var termRow = false
        val count = (0..info.third).count { y ->
            if (termRow) return@count false
            val d = points.map { it.distance(x to y) }.sum() < constraint
            if (d) {
                foundRow = true
                if (!found) found = true
            } else if (foundRow) {
                termRow = true
            }
            d
        }
        if (count == 0 && found) terminated = true
        count
    }
}

private fun genInfo(input: List<String>) =
    input.mapIndexed { i, x -> x.split(", ").map { it.toInt() }.let { Triple(it[0], it[1], i) } }.let {
        Triple(
            it,
            it.maxBy { x -> x.first }!!.first + 1,
            it.maxBy { x -> x.second }!!.second + 1
        )
    }

private fun Triple<Int, Int, Int>.distance(other: Pair<Int, Int>) =
    abs(first - other.first) + abs(second - other.second)