package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import com.github.coreyshupe.adventofcode.input
import org.junit.Test

import org.junit.Assert.*

class Day16KtTest {

    @Test
    fun runInstructionSetTest() {
        // no good example tests given
        "/2018/day16_input".asResource(ResourceType.Lined, {
            assertEquals(Pair(531, 649), runInstructionSet(it))
        }, this::class.java)
    }
}