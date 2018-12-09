package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.PointerNodeList
import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 9, ResourceType.Full) {
        val split = it.split(' ')
        val input = Pair(split[0].toInt(), split[6].toInt())
        println(playMarbles(input)) // part 1
        println(playMarbles(Pair(input.first, input.second * 100))) // part 2
    }
}

fun playMarbles(input: Pair<Int, Int>): Long {
    var pointer = PointerNodeList(0)
    var highestMarble = 0
    val map = mutableMapOf<Int, Long>()
    while (true) {
        (1..22).forEach { pointer = pointer.forward.insert(highestMarble + it) }
        for (x in 1..6) pointer = pointer.back
        highestMarble += 23
        if (highestMarble > input.second) break
        val turn = (highestMarble - 1) % input.first
        map[turn] = map.getOrDefault(turn, 0) + highestMarble + pointer.back.kill()
    }
    return map.values.max()!!
}