package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day5KtTest {

    private val input = """
0
3
0
1
-3
    """.trimIndent()

    @Test
    fun escapeMazeTest() {
        assertEquals(5, escapeMaze(input))
    }

    @Test
    fun escapeMazeDip3Test() {
        assertEquals(10, escapeMaze(input, dip = 3))
    }
}