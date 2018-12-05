package com.github.coreyshupe.adventofcode.y2015

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource

fun main(args: Array<String>) {
    "/2015/day1_input.txt".asResource(ResourceType.Full) {
        println(findFloor(it)) // part 1
        println(findBasementEntrance(it)) // part 2
    }
}

fun findFloor(input: String) = input.sumBy { if (it == '(') 1 else -1 }

fun findBasementEntrance(input: String): Int {
    var count = 0
    input.forEachIndexed { i, c ->
        count += if(c == '(') 1 else -1
        if(count < 0) return i + 1
    }
    throw IllegalArgumentException("Invalid input, failed to find basement.")
}