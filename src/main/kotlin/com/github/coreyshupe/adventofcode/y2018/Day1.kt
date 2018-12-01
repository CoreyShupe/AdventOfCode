package com.github.coreyshupe.adventofcode.y2018

fun applyFrequencies(input: String) = input.split('\n').map { it.toInt() }.sum()

fun findTwiceFrequency(input: String): Int {
    val frequencies = input.split('\n').map { it.toInt() }
    val seen = mutableSetOf<Int>()
    var iter = frequencies.iterator()
    return generateSequence(0) {
        seen.add(it)
        if (!iter.hasNext()) iter = frequencies.iterator()
        it + iter.next()
    }.find { seen.contains(it) }!!
}