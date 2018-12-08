package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day7KtTest {

    private val input = listOf(
        "Step C must be finished before step A can begin.",
        "Step C must be finished before step F can begin.",
        "Step A must be finished before step B can begin.",
        "Step A must be finished before step D can begin.",
        "Step B must be finished before step E can begin.",
        "Step D must be finished before step E can begin.",
        "Step F must be finished before step E can begin."
    )

    @Test
    fun findInstructionOrderTest() {
        assertEquals("CABDFE", findInstructionOrder(input))
    }

    @Test
    fun findInstructionTimeTest() {
        assertEquals(15, findInstructionTime(input, 2, 0))
    }
}