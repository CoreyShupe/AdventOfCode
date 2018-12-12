package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 12, ResourceType.Lined) {
        val initial = it[0].split(' ')[2]
        println(findActivePots(initial, it.drop(2), 20)) // part 1
        println(findActivePots(initial, it.drop(2), 50_000_000_000)) // part 2
    }
}

fun findActivePots(initial: String, input: List<String>, iters: Long): Long {
    val map = initial.mapIndexed { index, c -> index to c.check() }.toMap().toMutableMap()
    val rules = input.map {
        val split = it.split(" => ")
        val survives = split[1][0].check()
        FlowerPotRule(
            Pair(split[0][0].check(), split[0][1].check()),
            split[0][2].check(),
            Pair(split[0][3].check(), split[0][4].check()),
            survives
        )
    }.toSet()
    var last = 0
    var lastDiff = 0
    var count = 0
    (1..iters).forEach { z ->
        val change = mutableSetOf<Int>()
        for (x in (map.minBy { it.key }!!.key - 3)..(map.maxBy { it.key }!!.key + 3)) if (rules.contains(
                FlowerPotRule(
                    Pair(map.getOrDefault(x - 2, false), map.getOrDefault(x - 1, false)),
                    map.getOrDefault(x, false),
                    Pair(map.getOrDefault(x + 1, false), map.getOrDefault(x + 2, false)),
                    !map.getOrDefault(x, false)
                )
            )
        ) change.add(x)
        change.forEach { map[it] = !map.getOrDefault(it, false) }
        val curr = map.toList().sumBy { if (it.second) it.first else 0 }
        val diff = curr - last
        count = if (diff == lastDiff) (count + 1) else 0
        if (count == 25) return (((iters - z) * diff) + curr)
        lastDiff = diff
        last = curr
    }
    return map.toList().sumBy { if (it.second) it.first else 0 }.toLong()
}

private fun Char.check() = this == '#'

private data class FlowerPotRule(
    val left: Pair<Boolean, Boolean>,
    val curr: Boolean,
    val right: Pair<Boolean, Boolean>,
    val survives: Boolean
)