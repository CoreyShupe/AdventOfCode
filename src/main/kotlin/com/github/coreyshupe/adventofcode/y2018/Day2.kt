package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.asLinedResource

fun main(args: Array<String>) {
    "/2018/day2_input.txt".asLinedResource {
        println(countTwosAndThrees(it))
        println(findMostCommonString(it))
    }
}

fun countTwosAndThrees(input: List<String>): Int {
    val range = 1..2
    val arr = arrayOf(0, 0)
    input.forEach { str ->
        val map = str.groupingBy { it }.eachCount()
        range.forEach { x -> if (map.filter { it.value == x + 1 }.isNotEmpty()) arr[x - 1]++ }
    }
    return arr[0] * arr[1]
}

fun findMostCommonString(input: List<String>): String {
    fun String.difference(that: String): Int {
        val zipped = that.zip(this)
        if (zipped.count { it.first != it.second } != 1) return -1
        return zipped.withIndex().find { it.value.first != it.value.second }!!.index
    }
    input.forEachIndexed { index, first ->
        input.drop(index + 1).forEach { second ->
            val diff = first.difference(second)
            if (diff != -1) return second.removeRange(diff..diff)
        }
    }
    throw IllegalArgumentException("Illegal list of strings.")
}