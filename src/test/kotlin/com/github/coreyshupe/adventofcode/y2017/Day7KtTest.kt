package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day7KtTest {

    private val input = """
pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)
    """.trimIndent()

    @Test
    fun towerTopTest() {
        assertEquals("tknk", towerTop(input))
    }

    @Test
    fun watchTowerWeightTest() {
        assertEquals(60, watchTowerWeight(input))
    }
}