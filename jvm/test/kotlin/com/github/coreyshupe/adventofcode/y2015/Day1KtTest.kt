package com.github.coreyshupe.adventofcode.y2015

import org.junit.Test

import org.junit.Assert.*

class Day1KtTest {

    @Test
    fun findFloorTest() {
        assertEquals(0, findFloor("(())"))
        assertEquals(0, findFloor("()()"))
        assertEquals(3, findFloor("((("))
        assertEquals(3, findFloor("(()(()("))
        assertEquals(3, findFloor("))((((("))
        assertEquals(-1, findFloor("())"))
        assertEquals(-1, findFloor("))("))
        assertEquals(-3, findFloor(")))"))
        assertEquals(-3, findFloor(")())())"))
    }

    @Test
    fun findBasementEntranceTest() {
        assertEquals(1, findBasementEntrance(")"))
        assertEquals(5, findBasementEntrance("()())"))
    }
}