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

    @Test
    fun findMostCommonStringTest() {
        assertEquals(
            "fgij", findMostCommonString(
                """
abcde
fghij
klmno
pqrst
fguij
axcye
wvxyz
        """.trimIndent().split('\n')
            )
        )
    }
}