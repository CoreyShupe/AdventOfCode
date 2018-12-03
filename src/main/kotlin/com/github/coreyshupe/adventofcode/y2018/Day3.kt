package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource

fun main(args: Array<String>) {
    "/2018/day3_input.txt".asResource(ResourceType.Lined) {
        println(findOverlapping(it))
        println(findLoneStrand(it))
    }
}

data class Claim(val id: Int, val point: Pair<Int, Int>, val dims: Pair<Int, Int>)

fun loadClaim(input: String): Claim {
    val splits = input.split(' ')
    val id = splits[0].drop(1).toInt()
    val pointSplit = splits[2].dropLast(1).split(',')
    val point = Pair(pointSplit[0].toInt(), pointSplit[1].toInt())
    val dimSplit = splits[3].split('x')
    val dims = Pair(dimSplit[0].toInt(), dimSplit[1].toInt())
    return Claim(id, point, dims)
}

fun findOverlapping(input: List<String>): Int {
    return runThroughPoints(input.map { loadClaim(it) }) { _, point, map ->
        map[point] = if (map.containsKey(point)) map[point]!! + 1 else 1
        false
    }.count { it.value > 1 }
}

fun findLoneStrand(input: List<String>): Int {
    val claims = input.map { loadClaim(it) }
    val set = claims.map { it.id }.toMutableSet()
    runThroughPoints(claims) { id, point, map ->
        if (map.contains(point)) {
            set.remove(id)
            set.remove(map[point]!!)
            set.size == 1
        } else {
            map[point] = id
            false
        }
    }
    return set.first()
}

fun runThroughPoints(list: List<Claim>, mapper: (Int, Pair<Int, Int>, MutableMap<Pair<Int, Int>, Int>) -> Boolean):
        Map<Pair<Int, Int>, Int> {
    val map = mutableMapOf<Pair<Int, Int>, Int>()
    list.forEach {
        for (x in 0.until(it.dims.first)) for (z in 0.until(it.dims.second)) {
            if (mapper(it.id, Pair(x + it.point.first, z + it.point.second), map)) return map
        }
    }
    return map
}