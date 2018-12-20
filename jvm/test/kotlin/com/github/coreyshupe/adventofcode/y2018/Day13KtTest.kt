package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day13KtTest {

    @Test
    fun findFirstCrashTest() {
        val tracks = parseTracks("""/->-\
|   |  /----\
| /-+--+-\  |
| | |  | v  |
\-+-/  \-+--/
  \------/   """)
        assertEquals(Pair(7, 3), findFirstCrash(tracks.first, tracks.second))
    }

    @Test
    fun findLastRemainingTest() {
        val tracks = parseTracks("""/>-<\
|   |
| /<+-\
| | | v
\>+</ |
  |   ^
  \<->/""")
        assertEquals(Pair(6, 4), findLastRemaining(tracks.first, tracks.second))
    }
}