package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day1KtTest {

    @Test
    fun applyFrequenciesTest() {
        assertEquals(3, applyFrequencies("+1\n+1\n+1"))
        assertEquals(0, applyFrequencies("+1\n+1\n-2"))
        assertEquals(-6, applyFrequencies("-1\n-2\n-3"))
    }

    @Test
    fun findTwiceFrequencyTest() {
        assertEquals(0, findTwiceFrequency("+1\n-1"))
        assertEquals(10, findTwiceFrequency("+3\n+3\n+4\n-2\n-4"))
        assertEquals(5, findTwiceFrequency("-6\n+3\n+8\n+5\n-6"))
        assertEquals(14, findTwiceFrequency("+7\n+7\n-2\n-7\n-4"))
    }
}