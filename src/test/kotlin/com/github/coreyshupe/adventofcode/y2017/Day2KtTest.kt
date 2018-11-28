package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day2KtTest {

    @Test
    fun calculateChecksumTest() {
        assertEquals(
            18, calculateChecksum(
                """
            5 1 9 5
            7 5 3
            2 4 6 8
        """.trimIndent(), minMaxCalc
            )
        )
    }

    @Test
    fun calculateChecksumDivisibleTest() {
        assertEquals(
            9, calculateChecksum(
                """
            5 9 2 8
            9 4 7 3
            3 8 6 5
        """.trimIndent(), divisibleCalc
            )
        )
    }
}