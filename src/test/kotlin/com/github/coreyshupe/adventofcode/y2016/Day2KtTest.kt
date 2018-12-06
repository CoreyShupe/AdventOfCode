package com.github.coreyshupe.adventofcode.y2016

import org.junit.Test

import org.junit.Assert.*

class Day2KtTest {

    private val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")

    @Test
    fun findKeyCodeP1Test() {
        assertEquals("1985", findKeyCode(input, p1Arr, p1Start))
    }

    @Test
    fun findKeyCodeP2Test() {
        assertEquals("5DB3", findKeyCode(input, p2Arr, p2Start))
    }
}