package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day6KtTest {

    private val input = listOf("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9")

    @Test
    fun findLargestFiniteRegionTest() {
        assertEquals(17, findLargestFiniteRegion(input))
    }

    @Test
    fun findCentralRegionTest() {
        assertEquals(16, findCentralRegion(input, 32))
    }
}