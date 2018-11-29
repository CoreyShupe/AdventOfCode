package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day12KtTest {

    private val input = """
0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5
    """.trimIndent()

    @Test
    fun findAllInProgram0Test() {
        assertEquals(6, findAllInProgram0(input))
    }

    @Test
    fun countGroupsTest() {
        assertEquals(2, countGroups(input))
    }
}