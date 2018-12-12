package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 11, ResourceType.Full) {
        val pow = it.toInt()
        val input = Array(300) { x -> Array(300) { y -> calculate(x, y, pow) } }
        println(findBestPowerLevel3b3(input)) // part 1
        println(findBestPowerLevel(input)) // part 2
    }
}

fun findBestPowerLevel3b3(input: Array<Array<Int>>): Pair<Int, Int> {
    return input.mapIndexedNotNull { x, inner ->
        if (x > 297) 0 to Pair(x, 0)
        else inner.mapIndexed { y, _ ->
            if (y > 297) 0 to Pair(x, y) else {
                var ext = 0
                for (i in 0..2) for (j in 0..2) ext += input[x + i][y + j]
                ext to Pair(x, y)
            }
        }.maxBy { it.first }
    }.maxBy { it.first }!!.second
}

fun findBestPowerLevel(input: Array<Array<Int>>): Triple<Int, Int, Int> {
    val best = input.mapIndexedNotNull { x, inner ->
        inner.mapIndexed { y, _ ->
            var extended = 0
            (0..300).mapNotNull inner@{ i ->
                if (x + i >= 300 || y + i >= 300) return@inner null
                for (j in 0.until(i)) extended += input[x + i][y + j] + input[x + j][y + i]
                extended += input[x + i][y + i]
                (i + 1) to extended
            }.maxBy { it.second }!! to Pair(x, y)
        }.maxBy { it.first.second }
    }.maxBy { it.first.second }!!
    return Triple(best.second.first, best.second.second, best.first.first)
}

private fun calculate(x: Int, y: Int, input: Int) = (x + 10).let { (((((it * y) + input) * it) / 100) % 10) - 5 }