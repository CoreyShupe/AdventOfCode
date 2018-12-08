package com.github.coreyshupe.adventofcode.y2016

import org.junit.Test

import org.junit.Assert.*

class Day3KtTest {

    @Test
    fun isTrianglePossibleTest() {
        assert(!isTrianglePossible(listOf(10, 5, 25)))
        assert(isTrianglePossible(listOf(10, 5, 14)))
    }
}