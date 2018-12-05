package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import com.github.coreyshupe.adventofcode.group

fun main(args: Array<String>) {
    "/2018/day1_input.txt".asResource(ResourceType.Lined) {
        println(applyFrequencies(it)) // part 1
        println(findRepeatingFrequency(it)) // part 2
    }
}

fun applyFrequencies(input: List<String>) = input.map { it.toInt() }.sum()

fun findRepeatingFrequency(input: List<String>): Int {
    val parsedIter = input.map { it.toInt() }.iterator()

    // we want all of the frequencies as labeled
    val sums = generateSequence(0) {
        it + parsedIter.next()
    }.take(input.size + 1).toList()

    // if the recurring item is in the first iter we want to return it here
    val first = sums.groupingBy { it }.eachCount().toList().firstOrNull { it.second > 1 }
    if (first != null) return first.first

    val shift = sums[sums.size - 1]

    // if the shift is 0, then the recurring value is 0 along with -> else
    if (shift == 0) return 0

    // populate a map of the groups (value % shift) will be the key for groups
    val map = sums.dropLast(1).mapIndexed { i, j -> i to j }.group { it.second % shift }

    // These will be used later to find the best result
    fun Triple<Int, Int, Int>.compareTo(that: Triple<Int, Int, Int>) =
        if (this.first == that.first) this.second - that.second else this.first - that.first

    fun Triple<Int, Int, Int>.betterThan(that: Triple<Int, Int, Int>?) =
        that == null || this.first < that.first || (this.first == that.first && this.second < that.second)

    var minInfo: Triple<Int, Int, Int>? = null
    map.values.filter { it.size > 1 }.forEach { list ->
        // Pairs in these have first(index) second(frequency)
        // So we sort by the frequency
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
    return minInfo?.third ?: shift+1 // special case where no groups have more than 1 member defaults to shift + 1
}