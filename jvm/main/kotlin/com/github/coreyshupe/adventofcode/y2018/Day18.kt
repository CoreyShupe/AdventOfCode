package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import java.util.*

fun main(args: Array<String>) {
    input(18, 18, ResourceType.Full) {
        findResourceCollection(it).let(::println) // part 1
        findResourceCollection(it, 1_000_000_000).let(::println) // part 2
    }
}

fun findResourceCollection(input: String, minutes: Int = 10): Int {
    var array = input.split('\n').map { it.toCharArray().toTypedArray() }.toTypedArray()
    fun sumArr() = array.sumBy { it.count { it == '|' } } * array.sumBy { it.count { it == '#' } }
    fun getSafeTypeOf(indexX: Int, indexY: Int): Int {
        if (indexX < 0 || indexY < 0 || indexX >= array.size || indexY >= array[indexX].size) return -1
        return when (array[indexX][indexY]) {
            '#' -> 2 // open
            '|' -> 1 // trees
            else -> -1 // huh
        }
    }

    val hashes = mutableMapOf<Int, Pair<Int, Int>>()
    fun save(hashCode: Int, minute: Int): Pair<Boolean, Pair<Int, Int>> {
        return if (hashes.containsKey(hashCode)) true to hashes[hashCode]!!
        else {
            hashes[hashCode] = minute to sumArr()
            false to (-1 to -1)
        }
    }

    var minute = 0
    while (minute < minutes) {
        minute++
        // apply rules
        val newContents = Array(array.size) { x -> Array(array[x].size) { ' ' } }
        array.forEachIndexed { x, chars ->
            chars.forEachIndexed { y, c ->
                when (c) {
                    '.' -> {
                        var trees = 0
                        for (xOff in -1..1) {
                            for (yOff in -1..1) {
                                if (getSafeTypeOf(x + xOff, y + yOff) == 1) trees++
                                if (trees >= 3) break
                            }
                            if (trees >= 3) break
                        }
                        newContents[x][y] = if (trees >= 3) '|' else '.'
                    }
                    '|' -> {
                        var yards = 0
                        for (xOff in -1..1) {
                            for (yOff in -1..1) {
                                if (getSafeTypeOf(x + xOff, y + yOff) == 2) yards++
                                if (yards >= 3) break
                            }
                            if (yards >= 3) break
                        }
                        newContents[x][y] = if (yards >= 3) '#' else '|'
                    }
                    '#' -> {
                        var containsYard = false
                        var containsTrees = false
                        for (xOff in -1..1) {
                            for (yOff in -1..1) {
                                if (xOff == 0 && yOff == 0) continue
                                when (getSafeTypeOf(x + xOff, y + yOff)) {
                                    1 -> containsTrees = true
                                    2 -> containsYard = true
                                }
                                if (containsYard && containsTrees) break
                            }
                            if (containsYard && containsTrees) break
                        }
                        newContents[x][y] = if (containsTrees && containsYard) '#' else '.'
                    }
                }
            }
        }
        array = newContents
        val info = save(array.map { Arrays.deepHashCode(it) }.hashCode(), minute)
        if (info.first) {
            val start = info.second.first
            val map = hashes.values.filter { it.first >= start }.toMap()
            val fixedArr = Array(map.size) { map[start + it] }
            val left = minutes - minute
            return fixedArr[left % fixedArr.size]!!
        }
    }
    return sumArr()
}