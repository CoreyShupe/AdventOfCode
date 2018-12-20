package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day20KtTest {

    @Test
    fun determineBestRouteTest() {
        assertEquals(3, determineBestRoute(parseToStack("^WNE\$")))
        assertEquals(10, determineBestRoute(parseToStack("^ENWWW(NEEE|SSE(EE|N))\$")))
        assertEquals(18, determineBestRoute(parseToStack("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN\$")))
        assertEquals(23, determineBestRoute(parseToStack("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))\$")))
        assertEquals(
            31,
            determineBestRoute(parseToStack("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))\$"))
        )
    }
}