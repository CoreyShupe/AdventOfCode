package com.github.coreyshupe.adventofcode.y2017

fun inverseCaptcha(sequence: String, adder: Int = sequence.length / 2) =
    sequence.mapIndexed { index, c ->
        val response = index + adder
        val length = sequence.length
        Pair(c, if (response < length) response else response - length)
    }.filter { it.first == sequence[it.second] }.sumBy { "${it.first}".toInt() }