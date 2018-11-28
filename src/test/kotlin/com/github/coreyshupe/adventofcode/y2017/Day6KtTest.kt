package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day6KtTest {

    @Test
    fun reallocMemoryTest() {
        assertEquals(5, reallocMemory("0 2 7 0").first)
    }

    @Test
    fun reallocMemoryBlocksTest() {
        assertEquals(4, reallocMemory("0 2 7 0").second)
    }
}