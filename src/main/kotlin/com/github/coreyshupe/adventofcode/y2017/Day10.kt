package com.github.coreyshupe.adventofcode.y2017

fun knotHash(input: String): String {
    val lengths = input.map { it.toInt() }.toMutableList()
    lengths.addAll(arrayOf(17, 31, 73, 47, 23))
    val sparse = knotHashRounds(lengths, list = 0.until(256).toMutableList())
    val dense = mutableListOf<Int>()
    for (x in 0 until 16) {
        val i = x * 16
        dense.add(sparse.subList(i, i + 16).reduce { z, y -> z xor y })
    }
    return dense.joinToString {
        val hex = it.toString(16)
        if (hex.length < 2) "0$hex" else hex
    }.replace(", ", "")
}

tailrec fun knotHashRounds(
    lengths: List<Int>,
    position: Int = 0,
    skip: Int = 0,
    round: Int = 0,
    list: MutableList<Int>,
    rounds: Int = 64
): MutableList<Int> {
    var mPosition = position
    var mSkip = skip
    lengths.forEach { x ->
        var index = -1
        generateSequence {
            index += 1
            list[findNextIndex(mPosition, index, list.size)]
        }.takeWhile { index != x }
            .toList()
            .reversed()
            .forEachIndexed { i, j -> list[findNextIndex(mPosition, i, list.size)] = j }
        mPosition = findNextIndex(mPosition, x + mSkip, list.size)
        mSkip += 1
    }
    return if (round + 1 == rounds) list else knotHashRounds(lengths, mPosition, mSkip, round + 1, list, rounds)
}

fun findNextIndex(index: Int, adder: Int, length: Int): Int {
    var next = index + adder
    while (next >= length) {
        next -= length
    }
    return next
}