package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day15KtTest {

    @Test
    fun countGeneratorConnectionsTest() {
        assertEquals(588, countGeneratorConnections(65, 8921))
    }

    @Test
    fun countGeneratorConnectionsIndependentlyTest() {
        assertEquals(309, countGeneratorConnectionsIndependently(65, 8921))
    }
}