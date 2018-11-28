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
        assertEquals(true, checkPassphrase("abcde fghij", true))
        assertEquals(false, checkPassphrase("abcde xyz ecdab", true))
        assertEquals(true, checkPassphrase("a ab abc abd abf abj", true))
        assertEquals(true, checkPassphrase("iiii oiii ooii oooi oooo", true))
        assertEquals(false, checkPassphrase("oiii ioii iioi iiio", true))
    }
}