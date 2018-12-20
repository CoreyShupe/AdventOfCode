package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import org.junit.Test

import org.junit.Assert.*

class Day19KtTest {

    @Test
    fun findInstructionHaltTest() {
        // example badly represents this problem
        input(18, 19, ResourceType.Lined) {
            assertEquals(2072, findInstructionHalt(it))
            assertEquals(27578880, findInstructionHalt(it, 1))
        }
    }
}