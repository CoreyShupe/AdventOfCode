package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource

fun main(args: Array<String>) {
    "/2018/day4_input.txt".asResource(ResourceType.Lined) {
        println(findMostSleepingGuard(it)) // part 1
        println(findMinuteHoggingGuard(it)) // part 2
    }
}

private data class Guard(val id: Int, val times: MutableMap<Int, Int> = mutableMapOf())

fun findMostSleepingGuard(input: List<String>) = getGuards(input).reduce { x, z ->
    if (x.times.values.sum() > z.times.values.sum()) x else z
}.let { it.id * it.times.maxBy { x -> x.value }!!.key }

fun findMinuteHoggingGuard(input: List<String>) = getGuards(input).map { guard ->
    guard.id to guard.times.maxBy { it.value }
}.maxBy { it.second?.value ?: 0 }!!.let { it.first * it.second!!.key }

private fun getGuards(input: List<String>): List<Guard> {
    val guards = mutableMapOf<Int, Guard>()
    var currentGuard = Guard(-1)
    var lastTimeAsleep = -1
    input.sorted().forEach {
        when (it[19]) {
            'w' -> for (x in lastTimeAsleep.until("${it[15]}${it[16]}".toInt())) {
                currentGuard.times[x] = currentGuard.times.getOrDefault(x, 0) + 1
            }
            'f' -> lastTimeAsleep = "${it[15]}${it[16]}".toInt()
            'G' -> {
                val id = it.substring(26, it.indexOf(' ', 26)).toInt()
                if (!guards.containsKey(id)) {
                    guards[id] = Guard(id)
                }
                currentGuard = guards[id]!!
            }
            else -> throw IllegalArgumentException("Illegal action $it")
        }
    }
    return guards.values.toList()
}