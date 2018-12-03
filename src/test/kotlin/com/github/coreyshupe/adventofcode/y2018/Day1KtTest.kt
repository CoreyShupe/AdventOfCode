package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day1KtTest {

    @Test
    fun applyFrequenciesTest() {
        assertEquals(3, applyFrequencies("+1,+1,+1".split(',')))
        assertEquals(0, applyFrequencies("+1,+1,-2".split(',')))
        assertEquals(-6, applyFrequencies("-1,-2,-3".split(',')))
    }

    @Test
    fun findRepeatingFrequencyTest() {
        assertEquals(0, findRepeatingFrequency("+1,-1".split(',')))
        assertEquals(10, findRepeatingFrequency("+3,+3,+4,-2,-4".split(',')))
        assertEquals(5, findRepeatingFrequency("-6,+3,+8,+5,-6".split(',')))
        assertEquals(14, findRepeatingFrequency("+7,+7,-2,-7,-4".split(',')))
    }
}