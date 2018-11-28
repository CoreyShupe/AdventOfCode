package com.github.coreyshupe.adventofcode.y2017

val minMaxCalc: (list: List<Int>) -> Int = { it.max()!! - it.min()!! }
val divisibleCalc: (list: List<Int>) -> Int = {
    val value = it.mapNotNull { x ->
        val sub = it.find { z -> (z % x == 0 || x % z == 0) && z != x }
        if (sub == null) null else if (sub % x == 0) Pair(sub, x) else Pair(x, sub)
    }.first()
    value.first / value.second
}

fun calculateChecksum(spreadsheet: String, calcFunc: (list: List<Int>) -> Int): Int =
    spreadsheet.split("\n").map {
        calcFunc.invoke(it.split(Regex("[\t ]")).map { str -> str.toInt() })
    }.sum()