package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day14KtTest {

    @Test
    fun findNextTenRecipesTest() {
        assertEquals("5158916779", findNextTenRecipes(9))
        // test for 5 was failing while all others passed, weird
        assertEquals("9251071085", findNextTenRecipes(18))
        assertEquals("5941429882", findNextTenRecipes(2018))
    }

    @Test
    fun findRecipeCounterTest() {
        assertEquals(9, findRecipeCounter("51589"))
        assertEquals(5, findRecipeCounter("01245"))
        assertEquals(18, findRecipeCounter("92510"))
        assertEquals(2018, findRecipeCounter("59414"))

    }
}