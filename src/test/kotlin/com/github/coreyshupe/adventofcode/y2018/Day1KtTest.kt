package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day1KtTest {

    @Test
    fun applyFrequenciesTest() {
        assertEquals(3, applyFrequencies("+1\n+1\n+1".split('\n')))
        assertEquals(0, applyFrequencies("+1\n+1\n-2".split('\n')))
        assertEquals(-6, applyFrequencies("-1\n-2\n-3".split('\n')))
    }

    @Test
    fun findRepeatingFrequencyTest() {
        assertEquals(0, findRepeatingFrequency("+1\n-1".split('\n')))
        assertEquals(10, findRepeatingFrequency("+3\n+3\n+4\n-2\n-4".split('\n')))
        assertEquals(5, findRepeatingFrequency("-6\n+3\n+8\n+5\n-6".split('\n')))
        assertEquals(14, findRepeatingFrequency("+7\n+7\n-2\n-7\n-4".split('\n')))
    }
}