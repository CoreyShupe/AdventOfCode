package com.github.coreyshupe.adventofcode.y2017

fun escapeMaze(maze: String, dip: Int = 0): Int {
    val offsets = maze.split('\n').map { it.toInt() }.toIntArray()
    var index = 0
    var steps = 0
    while (index < offsets.size) {
        steps++
        val offset = offsets[index]
        offsets[index] = if (dip < 1 || offset < dip) offset + 1 else offset - 1
        index += offset
    }
    return steps
}