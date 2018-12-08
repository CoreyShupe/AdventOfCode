package com.github.coreyshupe.adventofcode.y2018

import org.junit.Assert.assertEquals
import org.junit.Test

class Day1KtTest {

    @Test
    fun applyFrequenciesTest() {
        assertEquals(3, applyFrequencies(listOf(1, 1, 1)))
        assertEquals(0, applyFrequencies(listOf(1, 1, -2)))
        assertEquals(-6, applyFrequencies(listOf(-1, -2, -3)))
    }

    @Test
    fun findRepeatingFrequencyTest() {
        assertEquals(0, findRepeatingFrequency(listOf(1, -1)))
        assertEquals(10, findRepeatingFrequency(listOf(3, 3, 4, -2, -4)))
        assertEquals(5, findRepeatingFrequency(listOf(-6, 3, 8, 5, -6)))
        assertEquals(14, findRepeatingFrequency(listOf(7, 7, -2, -7, -4)))
    }
}