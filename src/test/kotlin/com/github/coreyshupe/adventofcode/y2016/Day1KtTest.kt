package com.github.coreyshupe.adventofcode.y2016

import org.junit.Test

import org.junit.Assert.*

class Day1KtTest {

    @Test
    fun findDistanceTest() {
        assertEquals(5, findDistance(listOf("R2", "L3")))
        assertEquals(2, findDistance(listOf("R2", "R2", "R2")))
        assertEquals(12, findDistance(listOf("R5", "L5", "R5", "R3")))
    }

    @Test
    fun findLocationVisitedTwiceTest() {
        assertEquals(4, findLocationVisitedTwice(listOf("R8", "R4", "R4", "R8")))
    }
}