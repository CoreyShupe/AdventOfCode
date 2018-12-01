package com.github.coreyshupe.adventofcode.y2018

fun applyFrequencies(input: String) = frequenciesFromInput(input).sum()

fun findTwiceFrequency(input: String): Int {
    val frequencies = frequenciesFromInput(input)
    val seen = mutableSetOf<Int>()
    var iter = frequencies.iterator()
    return generateSequence(0) {
        seen.add(it)
        if (!iter.hasNext()) iter = frequencies.iterator()
        it + iter.next()
    }.find { seen.contains(it) }!!
}

private fun frequenciesFromInput(input: String) = input.split('\n').map { it.toInt() }