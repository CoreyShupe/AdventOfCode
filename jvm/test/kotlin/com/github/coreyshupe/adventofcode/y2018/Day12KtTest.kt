package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import org.junit.Test

import org.junit.Assert.*

class Day12KtTest {

    @Test
    fun findActivePotsTest() {
        // had to use these inputs, the examples given didn't really cover anything unit-testable
        // although these answers were confirmed by AoC
        input(18, 12, ResourceType.Lined) {
            val initial = it[0].split(' ')[2]
            assertEquals(3915, findActivePots(initial, it.drop(2), 20))
            assertEquals(4900000001793, findActivePots(initial, it.drop(2), 50_000_000_000))
        }
    }
}