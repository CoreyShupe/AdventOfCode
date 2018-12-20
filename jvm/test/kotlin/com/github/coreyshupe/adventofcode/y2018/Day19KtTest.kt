package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import org.junit.Assert.assertEquals
import kotlin.test.Ignore

class Day19KtTest {

    @Ignore
    fun findInstructionHaltTest() {
        // example badly represents this problem
        "/2018/day19_input".asResource(ResourceType.Lined, {
            assertEquals(2072, findInstructionHalt(it))
            assertEquals(27578880, findInstructionHalt(it, 1))
        }, this::class.java)
    }
}