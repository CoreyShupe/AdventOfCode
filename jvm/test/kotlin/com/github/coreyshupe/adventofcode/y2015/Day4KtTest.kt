package com.github.coreyshupe.adventofcode.y2015

import org.junit.Test

import org.junit.Assert.*

class Day4KtTest {

    @Test
    fun findHashWithLeadingZeroesTest() {
        assertEquals(609043, findHashWithLeadingZeroes("abcdef", 5))
        assertEquals(1048970, findHashWithLeadingZeroes("pqrstuv", 5))
    }
}