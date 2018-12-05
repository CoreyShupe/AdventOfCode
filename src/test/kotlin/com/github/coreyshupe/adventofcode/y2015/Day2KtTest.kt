package com.github.coreyshupe.adventofcode.y2015

import org.junit.Test

import org.junit.Assert.*

class Day2KtTest {

    @Test
    fun findNeededWrappingPaperTest() {
        assertEquals(58, findNeededWrappingPaper(listOf("2x3x4")))
        assertEquals(43, findNeededWrappingPaper(listOf("1x1x10")))
    }

    @Test
    fun findNeededRibbonTest() {
        assertEquals(34, findNeededRibbon(listOf("2x3x4")))
        assertEquals(14, findNeededRibbon(listOf("1x1x10")))
    }
}