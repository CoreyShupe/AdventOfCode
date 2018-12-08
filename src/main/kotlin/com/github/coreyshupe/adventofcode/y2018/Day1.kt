package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import com.github.coreyshupe.adventofcode.group

fun main(args: Array<String>) {
    "/2018/day1_input".asResource(ResourceType.Lined) {
        val input = it.map { x -> x.toInt() }
        println(applyFrequencies(input)) // part 1
        println(findRepeatingFrequency(input)) // part 2
    }
}

fun applyFrequencies(input: List<Int>) = input.sum()

fun findRepeatingFrequency(input: List<Int>): Int {
    val parsedIter = input.iterator()

    val sums = generateSequence(0) {
        it + parsedIter.next()
    }.take(input.size + 1).toList()

    val first = sums.groupingBy { it }.eachCount().toList().firstOrNull { it.second > 1 }
    if (first != null) return first.first

    val shift = sums[sums.size - 1]

    if (shift == 0) return 0

    val map = sums.dropLast(1).mapIndexed { i, j -> i to j }.group { it.second % shift }

    fun Triple<Int, Int, Int>.compareTo(that: Triple<Int, Int, Int>) =
        if (this.first == that.first) this.second - that.second else this.first - that.first

    fun Triple<Int, Int, Int>.betterThan(that: Triple<Int, Int, Int>?) =
        that == null || this.first < that.first || (this.first == that.first && this.second < that.second)

    var minInfo: Triple<Int, Int, Int>? = null
    map.values.filter { it.size > 1 }.forEach { list ->
        val sortedList = list.sortedBy { it.second }
        val min = sortedList.drop(1).mapIndexed { i, pair ->
            val previous = sortedList[i]
            Triple(
                pair.second - previous.second,
                if (shift > 0) previous.first else pair.first,
                if (shift > 0) pair.second else previous.second
            )
        }.minWith(Comparator(Triple<Int, Int, Int>::compareTo))!!
        if (min.betterThan(minInfo)) minInfo = min
    }
    return minInfo?.third ?: shift+1
}