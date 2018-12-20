package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.*

val globalBackup = mutableSetOf<Pair<Int, Int>>()

fun main(args: Array<String>) {
    input(18, 17, ResourceType.Lined) {
        countWaterTiles(loadPointArray(it)).let(::println) // part 1, part 2
    }
}

fun countWaterTiles(input: Set<Pair<Int, Int>>): Pair<Int, Int> {
    globalBackup.clear()
    val minX = input.minBy { it.first }!!.first - 1
    val maxX = input.maxBy { it.first }!!.first + 1
    val minY = input.minBy { it.second }!!.second
    val maxY = input.maxBy { it.second }!!.second
    val arr = Array(maxY + 1) { _ -> Array((maxX - minX) + 1) { _ -> '.' } } // y,x
    arr[0][500 - minX] = '+'
    input.forEach { arr[it.second][it.first - minX] = '#' }
    dropDown(arr, 0 to 500 - minX)
    globalBackup.forEach { arr[it.first][it.second] = '~' }
    return arr.sumBy { innerArray -> innerArray.count { it == '~' || it == '|' } } - (minY - 1) to
            arr.sumBy { innerArray -> innerArray.count { it == '~' } }
}

fun dropDown(array: Array<Array<Char>>, initPointer: Pair<Int, Int>) {
    val pointer = Pointer(initPointer)
    while (when (array.at(pointer.mutate(1))) {
            '~', '#' -> false
            '|' -> return
            else -> true
        }
    ) {
        array.setAt(pointer, '|')
        if (pointer.peek(1).first == array.size) return
    }
    pointer.mutate(-1)
    while (spread(array, pointer.pointer) == '~') pointer.mutate(-1)
}

fun spread(array: Array<Array<Char>>, initPointer: Pair<Int, Int>): Char {
    var dropped = false
    var min = initPointer.second
    var max = initPointer.second
    fun affect(left: Boolean) {
        val appender = if (left) -1 else 1
        val pointer = Pointer(initPointer)
        fun fixMax() {
            if (left) min = pointer.pointer.second
            else max = pointer.pointer.second
        }
        while (true) {
            if (when (array.at(pointer.mutate(y = appender))) {
                    '.' -> {
                        fixMax()
                        when (array.at(pointer.peek(1))) {
                            '.' -> {
                                dropped = true
                                dropDown(array, pointer.pointer)
                                true
                            }
                            '|' -> {
                                array.setAt(pointer.peek(1), '~')
                                false
                            }
                            else -> false
                        }
                    }
                    '~', '|' -> {
                        fixMax()
                        if (array.at(pointer.peek(1)) == '|') array.setAt(pointer.peek(1), '~')
                        false
                    }
                    '#' -> true
                    else -> throw IllegalStateException("huh?")
                }
            ) break
        }
    }
    affect(true)
    affect(false)
    val used = if (dropped) '|' else '~'
    (min..max).forEach {
        if (array[initPointer.first][it] == '~') {
            globalBackup.add(initPointer.first to it)
        }
        array[initPointer.first][it] = used
    }
    return array.at(initPointer)
}

fun loadPointArray(input: List<String>) = input.flatMap {
    val split = it.split(", ")
    val xShift = split[0][0] == 'x'
    val placement = split[0].drop(2).toInt()
    val range = split[1].drop(2).split("..").map(String::toInt)
    for (place in range[0]..range[1]) {

    }
    (range[0]..range[1]).map { latter ->
        if (xShift) placement to latter
        else latter to placement
    }
}.toSet()