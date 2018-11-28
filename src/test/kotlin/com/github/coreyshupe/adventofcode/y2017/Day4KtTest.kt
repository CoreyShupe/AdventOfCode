package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day4KtTest {

    @Test
    fun checkPassphraseTest() {
        assertEquals(true, checkPassphrase("aa bb cc dd ee"))
        assertEquals(false, checkPassphrase("aa bb cc dd aa"))
        assertEquals(true, checkPassphrase("aa bb cc dd aaa"))
    }

    @Test
    fun checkIgnoreLocationPassphraseTest() {
    }
}