package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day8KtTest {
    private val input = """
b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10
        """.trimIndent()

    @Test
    fun runRegisterInstructionsTest() {
        assertEquals(1, runRegisterInstructions(input).first)
    }

    @Test
    fun runRegisteredInstructionsTest() {
        assertEquals(10, runRegisterInstructions(input).second)
    }
}