package com.github.coreyshupe.adventofcode.y2017

fun reallocMemory(sequence: String): Pair<Int, Int> {
    val memoryBlocks = sequence.split(' ', '\t').map { it.toInt() }.toMutableList()
    val lastSeen = mutableListOf(memoryBlocks.toIntArray().joinToString(""))
    var steps = 0
    while (true) {
        steps++
        val intArrString = mutateBlockToNextState(memoryBlocks)
        if (lastSeen.contains(intArrString)) {
            var blocks = 0
            while (mutateBlockToNextState(memoryBlocks) != intArrString) {
                blocks++
            }
            return Pair(steps, blocks + 1)
        }
        lastSeen.add(intArrString)
    }
}

fun mutateBlockToNextState(list: MutableList<Int>): String {
    val max = list.max()!!
    val index = list.indexOf(max)
    val each = max / list.size
    val remainder = max % list.size
    list[index] = 0
    list.replaceAll { it + each }
    var place = index
    for (x in 1..remainder) {
        place = if (place + 1 == list.size) 0 else place + 1
        list[place] += 1
    }
    return list.toIntArray().joinToString("")
}