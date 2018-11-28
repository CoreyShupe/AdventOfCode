package com.github.coreyshupe.adventofcode.y2017

fun processStream(stream: String): Pair<Int, Int> {
    var total = 0
    var depth = 0
    var index = 0
    var garbage = 0
    while (index < stream.length) {
        when (stream[index]) {
            '!' -> index += 1
            '<' -> {
                index += 1
                while (stream[index] != '>') {
                    if (stream[index] == '!') {
                        index += 2
                        continue
                    }
                    garbage += 1
                    index += 1
                }
            }
            '{' -> {
                depth += 1
                total += depth
            }
            '}' -> depth -= 1
        }
        index += 1
    }
    return Pair(total, garbage)
}