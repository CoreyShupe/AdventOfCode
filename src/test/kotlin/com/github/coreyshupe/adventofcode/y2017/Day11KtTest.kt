package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day11KtTest {

    @Test
    fun stepsFromPathTest() {
        assertEquals(3, stepsFromPath("ne,ne,ne").first)
        assertEquals(0, stepsFromPath("ne,ne,sw,sw").first)
        assertEquals(2, stepsFromPath("ne,ne,s,s").first)
        assertEquals(3, stepsFromPath("se,sw,se,sw,sw").first)
    }
}