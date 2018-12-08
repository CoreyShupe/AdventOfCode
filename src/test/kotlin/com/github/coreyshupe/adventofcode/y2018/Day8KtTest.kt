package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day8KtTest {

    private val input = listOf("2", "3", "0", "3", "10", "11", "12", "1", "1", "0", "1", "99", "2", "1", "1", "2")

    @Test
    fun countMetaDataStatsTest() {
        assertEquals(138, countMetaDataStats(input))
    }

    @Test
    fun countRootNodeDataTest() {
        assertEquals(66, countRootNodeData(input))
    }
}