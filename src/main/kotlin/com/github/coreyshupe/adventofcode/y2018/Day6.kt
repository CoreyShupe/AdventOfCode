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

fun findLargestFiniteRegion(input: List<String>): Any {
    val points =
        input.mapIndexed { i, j -> j.split(',').map { it.trim().toInt() }.let { Triple(it[0], it[1], i) } }
            .toMutableList()
    val maxX = points.maxBy { it.first }!!.first + 1
    val maxY = points.maxBy { it.second }!!.second + 1
    val ignored = mutableSetOf(-1)
    val mapper = mutableMapOf<Int, Int>()
    for (x in 0.until(maxX)) {
        for (y in 0.until(maxY)) {
            val mapped = points.map { (it.first to it.second).distance(x to y) to it }
            val filtered = mapped.filter { it.first == mapped.minBy { x -> x.first }!!.first }
            if (filtered.size != 1) continue
            val value = filtered.first().second.third
            if (ignored.contains(value)) continue
            if ((y == 0 || y == maxY - 1) || (x == 0 || x == maxX - 1)) {
                ignored.add(value)
                mapper.remove(value)
            } else mapper[value] = mapper.getOrDefault(value, 0) + 1
        }
    }
    return mapper.maxBy { it.value }!!.value
}

fun findCentralRegion(input: List<String>, constraint: Int): Int {
    val points = input.map { i -> i.split(',').map { it.trim().toInt() }.let { it[0] to it[1] } }
    val maxX = points.maxBy { it.first }!!.first
    val maxY = points.maxBy { it.second }!!.second
    return (0..maxX).sumBy { x -> (0..maxY).count { y -> points.map { it.distance(x to y) }.sum() < constraint } }
}

private fun Pair<Int, Int>.distance(other: Pair<Int, Int>) = abs(first - other.first) + abs(second - other.second)