package com.github.coreyshupe.adventofcode.y2015

import org.junit.Test

import org.junit.Assert.*

class Day3KtTest {

    @Test
    fun findHouseCountTest() {
        assertEquals(2, findHouseCount(">"))
        assertEquals(4, findHouseCount("^>v<"))
        assertEquals(2, findHouseCount("^v^v^v^v^v"))
    }

    @Test
    fun findHouseCountWithRobotTest() {
        assertEquals(3, findHouseCountWithRobot("^v"))
        assertEquals(3, findHouseCountWithRobot("^>v<"))
        assertEquals(11, findHouseCountWithRobot("^v^v^v^v^v"))
    }
}