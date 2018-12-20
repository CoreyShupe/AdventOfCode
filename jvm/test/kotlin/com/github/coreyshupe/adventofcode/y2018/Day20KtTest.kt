package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day20KtTest {

    @Test
    fun determineRouteDataTest() {
        fun assert(expected: Int, input: String) = input.byteInputStream().use {
            assertEquals(expected, determineRouteData(it).first)
        }
        assert(3, "WNE")
        assert(10, "ENWWW(NEEE|SSE(EE|N))")
        assert(18, "ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN")
        assert(23, "ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))")
        assert(31, "WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))")

    }
}