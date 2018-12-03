package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test
import kotlin.test.assertEquals

class Day2KtTest {

    @Test
    fun countTwosAndThreesTest() {
        assertEquals(
            12, countTwosAndThrees(
                listOf(
                    "abcdef",
                    "bababc",
                    "abbcde",
                    "abcccd",
                    "aabcdd",
                    "abcdee",
                    "ababab"
                )
            )
        )
    }

    private val commonStringInput = listOf(
        "abcde",
        "fghij",
        "klmno",
        "pqrst",
        "fguij",
        "axcye",
        "wvxyz"
    )

    @Test
    fun findMostCommonStringTest() {
        assertEquals("fgij", findMostCommonString(commonStringInput))
    }

    @Test
    fun findMostCommonStringRemovalTest() {
        assertEquals("fgij", findMostCommonStringRemoval(commonStringInput))
    }
}