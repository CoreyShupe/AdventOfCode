package com.github.coreyshupe.adventofcode.y2016

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(16, 2, ResourceType.Lined) {
        println(findKeyCode(it, p1Arr, p1Start)) // part 1
        println(findKeyCode(it, p2Arr, p2Start)) // part 2
    }
}

val p1Arr = arrayOf(
    arrayOf<Char?>('1', '2', '3'),
    arrayOf<Char?>('4', '5', '6'),
    arrayOf<Char?>('7', '8', '9')
)
val p1Start = Pair(1, 1)
val p2Arr = arrayOf(
    arrayOf(null, null, '1', null, null),
    arrayOf(null, '2', '3', '4', null),
    arrayOf<Char?>('5', '6', '7', '8', '9'),
    arrayOf(null, 'A', 'B', 'C', null),
    arrayOf(null, null, 'D', null, null)
)
val p2Start = Pair(2, 0)

fun findKeyCode(input: List<String>, arr: Array<Array<Char?>>, start: Pair<Int, Int>) = buildString {
    var pointer = start
    input.forEach { str ->
        str.map {
            when (it) {
                'L' -> KeyDirection.LEFT
                'U' -> KeyDirection.UP
                'R' -> KeyDirection.RIGHT
                'D' -> KeyDirection.DOWN
                else -> throw IllegalArgumentException("Failed to parse instruction $it.")
            }
        }.forEach { pointer = it.safelyMovePointer(arr, pointer) }
        append(arr[pointer.first][pointer.second])
    }
}

private enum class KeyDirection(val alt: (x: Pair<Int, Int>) -> Pair<Int, Int>) {
    LEFT({ x -> Pair(x.first, x.second - 1) }),
    RIGHT({ x -> Pair(x.first, x.second + 1) }),
    UP({ x -> Pair(x.first - 1, x.second) }),
    DOWN({ x -> Pair(x.first + 1, x.second) });

    fun safelyMovePointer(arr: Array<Array<Char?>>, pair: Pair<Int, Int>): Pair<Int, Int> {
        val next = alt(pair)
        return if (next.first < 0 ||
            next.second < 0 ||
            next.first >= arr.size ||
            next.second >= arr[0].size ||
            arr[next.first][next.second] == null
        ) pair else next
    }
}