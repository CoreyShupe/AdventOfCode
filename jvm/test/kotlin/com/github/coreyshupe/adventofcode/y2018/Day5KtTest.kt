package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day5KtTest {

    private val input = "dabAcCaCBAcCcaDA"

    @Test
    fun countFromReducedPolymerTest() {
        assertEquals(10, countFromReducedPolymer(input))
    }

    @Test
    fun countFromBetterReducedPolymerTest() {
        assertEquals(4, countFromBetterReducedPolymer(input))
    }
}