package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.input
import org.junit.Test

import org.junit.Assert.*

class Day16KtTest {

    @Test
    fun runInstructionSetTest() {
        // no good example tests given
        input(18, 16, ResourceType.Lined) {
            assertEquals(Pair(531, 649), runInstructionSet(it))
        }
    }
}