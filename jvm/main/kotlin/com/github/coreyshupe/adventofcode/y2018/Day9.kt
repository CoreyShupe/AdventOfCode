package com.github.coreyshupe.adventofcode.y2018

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
    var pointer = RotatingNodeList()
    var highestMarble = 0
    val map = mutableMapOf<Int, Long>()
    while (true) {
        (1..22).forEach { pointer = pointer.next.insert(highestMarble + it) }
        for (x in 1..6) pointer = pointer.previous
        highestMarble += 23
        if (highestMarble > input.second) break
        val turn = (highestMarble - 1) % input.first
        val removing = pointer.previous
        removing.previous.next = pointer
        pointer.previous = removing.previous
        map[turn] = map.getOrDefault(turn, 0) + highestMarble + removing.value
    }
    return map.values.max()!!
}

private class RotatingNodeList {
    var previous = this
    var next = this
    private var etc = 0
    val value get() = etc

    fun insert(value: Int): RotatingNodeList {
        val new = RotatingNodeList()
        new.etc = value
        new.next = next
        next.previous = new
        next = new
        new.previous = this
        return new
    }
}