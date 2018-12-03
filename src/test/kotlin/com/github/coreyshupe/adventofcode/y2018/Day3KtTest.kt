package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day3KtTest {

    private val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")

    @Test
    fun findOverlappingTest() {
        assertEquals(4, findOverlapping(input))
    }

    @Test
    fun findLoneStrandTest() {
        assertEquals(3, findLoneStrand(input))
    }
}