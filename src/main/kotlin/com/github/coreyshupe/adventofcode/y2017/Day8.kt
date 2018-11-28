package com.github.coreyshupe.adventofcode.y2017

val conditionalFunctions = mapOf<String, (pair: Pair<Int, Int>) -> Boolean>(
    Pair("==", { pair -> pair.first == pair.second }),
    Pair("!=", { pair -> pair.first != pair.second }),
    Pair("<=", { pair -> pair.first <= pair.second }),
    Pair(">=", { pair -> pair.first >= pair.second }),
    Pair("<", { pair -> pair.first < pair.second }),
    Pair(">", { pair -> pair.first > pair.second })
)

fun runRegisterInstructions(instructions: String): Pair<Int, Int> {
    val map = mutableMapOf<String, Int>()
    var max = 0
    instructions.split('\n').forEach { instruction ->
        val arr = instruction.split(' ', '\t')
        val comparingPair = Pair(map.getOrDefault(arr[4], 0), arr[6].toInt())
        if (conditionalFunctions[arr[5]]!!.invoke(comparingPair)) {
            val register = arr[0]
            val increase = arr[1] == "inc"
            val value = arr[2].toInt()
            map[register] = map.getOrDefault(register, 0) + (if (increase) value else -value)
            max = kotlin.math.max(map[register]!!, max)
        }
    }
    return Pair(map.values.max()!!, max)
}