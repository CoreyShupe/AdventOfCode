package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day17KtTest {

    @Test
    fun countWaterTilesTest() {
        assertEquals(Pair(57, 29), countWaterTiles(loadPointArray("""x=495, y=2..7
y=7, x=495..501
x=501, y=3..7
x=498, y=2..4
x=506, y=1..2
x=498, y=10..13
x=504, y=10..13
y=13, x=498..504""".split('\n'))))
    }
}