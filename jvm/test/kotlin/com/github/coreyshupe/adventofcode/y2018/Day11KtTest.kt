package com.github.coreyshupe.adventofcode.y2018

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class Day11KtTest {

    private val serial18 = serialToArrays(18)
    private val serial42 = serialToArrays(42)

    @Test
    fun findBestPowerLevel3b3Test() {
        assertEquals(Pair(33, 45), findBestPowerLevel3b3(serial18))
    }

    @Test
    fun findBestPowerLevelTest() {
        assertEquals(Triple(90, 269, 16), findBestPowerLevel(serial18))
        assertEquals(Triple(232, 251, 12), findBestPowerLevel(serial42))
    }

    @Ignore
    private fun serialToArrays(serial: Int): Array<Array<Int>> {
        return Array(300) { x -> Array(300) { y -> calculate(x, y, serial) } }
    }

    @Ignore
    private fun calculate(x: Int, y: Int, input: Int) = (x + 10).let { (((((it * y) + input) * it) / 100) % 10) - 5 }
}