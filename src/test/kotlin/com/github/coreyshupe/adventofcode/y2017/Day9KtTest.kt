package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day9KtTest {

    @Test
    fun processStreamTest() {
        assertEquals(1, processStream("{}").first)
        assertEquals(6, processStream("{{{}}}").first)
        assertEquals(5, processStream("{{},{}}").first)
        assertEquals(16, processStream("{{{},{},{{}}}}").first)
        assertEquals(1, processStream("{<a>,<a>,<a>,<a>}").first)
        assertEquals(9, processStream("{{<ab>},{<ab>},{<ab>},{<ab>}}").first)
        assertEquals(9, processStream("{{<!!>},{<!!>},{<!!>},{<!!>}}").first)
        assertEquals(3, processStream("{{<a!>},{<a!>},{<a!>},{<ab>}}").first)
    }

    @Test
    fun processStreamGarbageTest() {
        assertEquals(0, processStream("<>").second)
        assertEquals(17, processStream("<random characters>").second)
        assertEquals(3, processStream("<<<<>").second)
        assertEquals(2, processStream("<{!>}>").second)
        assertEquals(0, processStream("<!!>").second)
        assertEquals(0, processStream("<!!!>>").second)
        assertEquals(10, processStream("<{o\"i!a,<{i<a>").second)
    }
}