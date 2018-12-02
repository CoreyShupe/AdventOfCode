package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.asLinedResource

fun main(args: Array<String>) {
    "/2018/day1_input.txt".asLinedResource {
        println(applyFrequencies(it))
        println(findRepeatingFrequency(it))
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
    val map = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    sums.dropLast(1).forEachIndexed { index, i ->
        val mod = i % shift
        if (!map.containsKey(mod)) map[mod] = mutableListOf()
        map[mod]!!.add(Pair(index, i))
    }

    // These will be used later to find the best result
    var minimumIndex: Int? = null
    var minimumDifference: Int? = null
    var minimumFrequency: Int? = null
    map.values.forEach { list ->
        // Pairs in these have first(index) second(frequency)
        // So we sort by the frequency
        val sortedList = list.sortedBy { it.second }
        sortedList.forEachIndexed { i, pair ->
            if (i != 0) { // skip first, we can't compare that stuff
                val previous = sortedList[i - 1]
                val difference = pair.second - previous.second
                val index = if (shift > 0) previous.first else pair.first
                val frequency = if (shift > 0) pair.second else previous.second

                // check if there is a minimum difference
                // check if the minimum difference between groups is good enough
                // check if the difference == but index is better
                if (minimumDifference == null || difference < minimumDifference!! ||
                    (difference == minimumDifference && index < minimumIndex!!)
                ) {
                    // set all mins
                    minimumDifference = difference
                    minimumFrequency = frequency
                    minimumIndex = index
                }
            }
        }
    }
    return minimumFrequency ?: shift+1 // special case where no groups have more than 1 member defaults to shift + 1
}