package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day9KtTest {

    @Test
    fun playMarblesTest() {
        assertEquals(8317L, playMarbles(Pair(10, 1618)))
        assertEquals(146373L, playMarbles(Pair(13, 7999)))
        assertEquals(2764L, playMarbles(Pair(17, 1104)))
        assertEquals(54718L, playMarbles(Pair(21, 6111)))
        assertEquals(37305L, playMarbles(Pair(30, 5807)))
    }
}