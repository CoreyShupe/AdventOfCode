package com.github.coreyshupe.adventofcode.y2017

import kotlin.math.max

fun countOnBits(input: String) = countOnBits(generateBitMap(input))

fun countOnBits(bitMap: List<List<Boolean>>) = bitMap.sumBy { it.sumBy { x -> if (x) 1 else 0 } }

fun countRegions(input: String): Int {
    fun isValidLocation(x: Int, y: Int) = x >= 0 && y >= 0 && x <= 127 && y <= 127

    fun MutableList<MutableList<Boolean>>.destoryGroup(x: Int, y: Int) {
        this[x][y] = false
        if (isValidLocation(x + 1, y) && this[x + 1][y]) destoryGroup(x + 1, y)
        if (isValidLocation(x - 1, y) && this[x - 1][y]) destoryGroup(x - 1, y)
        if (isValidLocation(x, y + 1) && this[x][y + 1]) destoryGroup(x, y + 1)
        if (isValidLocation(x, y - 1) && this[x][y - 1]) destoryGroup(x, y - 1)
    }

    val bitMap = generateBitMap(input)
    var regions = 0
    var terminate = false
    for (x in 0..127) {
        for (y in 0..127) {
            if (bitMap[x][y]) {
                regions += 1
                bitMap.destoryGroup(x, y)
                if (countOnBits(bitMap) == 0) {
                    terminate = true
                    break
                }
            }
        }
        if (terminate) break
    }
    return regions
}

fun generateBitMap(input: String): MutableList<MutableList<Boolean>> {
    fun String.populate(of: Char, reqLength: Int) = "${"$of".repeat(max(reqLength - this.length, 0))}$this"
    fun Char.hexToBinary() = "$this".toInt(16).toString(2).populate('0', 4)
    return (0..127).map {
        knotHash("$input-$it").map { x ->
            x.hexToBinary()
        }.joinToString("").map { x -> x == '1' }.toMutableList()
    }.toMutableList()
}