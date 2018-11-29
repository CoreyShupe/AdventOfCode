package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day10KtTest {

    @Test
    fun knotHashTest() {
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", knotHash(""))
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", knotHash("AoC 2017"))
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", knotHash("1,2,3"))
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", knotHash("1,2,4"))
    }

    @Test
    fun knotHashRoundsTest() {
        assertEquals(12, listOf(3, 4, 1, 5).let {
            val hashed = knotHashRounds(it, list = 0.until(5).toMutableList(), rounds = 1)
            hashed[0] * hashed[1]
        })
    }
}