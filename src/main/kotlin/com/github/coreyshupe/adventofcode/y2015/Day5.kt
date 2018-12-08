package com.github.coreyshupe.adventofcode.y2015

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource

fun main(args: Array<String>) {
    "/2015/day5_input".asResource(ResourceType.Lined) {
        println(countNiceStrings(it)) // part 1 to 2
    }
}

fun countNiceStrings(input: List<String>) = input.count(::isNiceP1) to input.count(::isNiceP2)

fun isNiceP1(input: String): Boolean {
    if (input.contains(Regex("(ab)|(cd)|(pq)|(xy)"))) return false
    if (input.count { "aeiou".contains(it, true) } < 3) return false
    for (x in 'a'..'z') {
        if (input.contains("$x$x")) return true
    }
    return false
}

fun isNiceP2(input: String): Boolean {
    val patterns = mutableMapOf<String, Int>()
    var hasRepeater = false
    var hasPattern = false
    input.forEachIndexed { index, c ->
        if (!hasRepeater) {
            if (index < input.length - 2) {
                if (c == input[index + 2]) {
                    if (hasPattern) return true
                    hasRepeater = true
                }
            } else if (!hasRepeater) return false
        }
        if (!hasPattern) {
            if (index < input.length - 1) {
                val pattern = "$c${input[index + 1]}"
                if (patterns.containsKey(pattern)) {
                    if (patterns[pattern] != index) {
                        if (hasRepeater) return true
                        hasPattern = true
                    }
                } else {
                    patterns[pattern] = index + 1
                }
            } else if (!hasPattern) return false
        }
    }
    return false
}