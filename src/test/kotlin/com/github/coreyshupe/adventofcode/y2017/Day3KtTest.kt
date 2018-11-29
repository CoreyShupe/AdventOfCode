package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day3KtTest {

    @Test
    fun checkSpiralMemoryTest() {
        assertEquals(0, checkSpiralMemory(1))
        assertEquals(3, checkSpiralMemory(12))
        assertEquals(2, checkSpiralMemory(23))
        assertEquals(31, checkSpiralMemory(1024))
    }

    @Test
    fun findValueWritTest() {
        assertEquals(10, findValueWrit(5))
        assertEquals(147, findValueWrit(142))
    }
}