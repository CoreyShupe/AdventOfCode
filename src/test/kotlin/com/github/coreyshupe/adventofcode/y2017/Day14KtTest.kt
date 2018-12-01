package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day14KtTest {

    private val input = "flqrgnkx"

    @Test
    fun countOnBitsTest() {
        assertEquals(8108, countOnBits(input))
    }

    @Test
    fun countRegionsTest() {
        assertEquals(1242, countRegions(input))
    }
}