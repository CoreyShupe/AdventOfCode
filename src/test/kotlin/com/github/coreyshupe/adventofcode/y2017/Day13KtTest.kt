package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day13KtTest {

    private val input = """
0: 3
1: 2
4: 4
6: 4
    """.trimIndent()

    @Test
    fun predictSeverityTest() {
        assertEquals(24, predictSeverity(input))
    }

    @Test
    fun findNecessaryDelayTest() {
        assertEquals(10, findNecessaryDelay(input))
    }
}