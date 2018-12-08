package com.github.coreyshupe.adventofcode.y2016

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource

fun main(args: Array<String>) {
    "/2016/day3_input".asResource(ResourceType.Lined) {
        println(findAllPossibleTriangles(it)) // part 1
        println(findAllPossibleRowedTriangles(it)) // part 2
    }
}

fun findAllPossibleTriangles(input: List<String>) =
    input.count { x -> isTrianglePossible(x.split(' ').filter { !it.isBlank() }.map { it.toInt() }) }

fun findAllPossibleRowedTriangles(input: List<String>): Int {
    val usable = input.map { x -> x.split(' ').filter { !it.isBlank() }.map { it.toInt() } }
    var count = 0
    for (x in 0.until(usable.size) step 3) {
        for (z in 0..2) {
            if (isTrianglePossible(listOf(usable[x][z], usable[x + 1][z], usable[x + 2][z]))) count++
        }
    }
    return count
}

fun isTrianglePossible(list: List<Int>): Boolean {
    val x = list[0]
    val y = list[1]
    val z = list[2]
    return x + y > z && x + z > y && z + y > x
}