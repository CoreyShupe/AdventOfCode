package com.github.coreyshupe.adventofcode.y2017

import org.junit.Test

import org.junit.Assert.*

class Day1KtTest {
    
    @Test
    fun inverseCaptchaTest() {
        assertEquals(3, inverseCaptcha("1122", 1))
        assertEquals(4, inverseCaptcha("1111", 1))
        assertEquals(0, inverseCaptcha("1234", 1))
        assertEquals(9, inverseCaptcha("91212129", 1))
    }

    @Test
    fun inverseCaptchaHalfwayTest() {
        assertEquals(6, inverseCaptcha("1212"))
        assertEquals(0, inverseCaptcha("1221"))
        assertEquals(4, inverseCaptcha("123425"))
        assertEquals(12, inverseCaptcha("123123"))
        assertEquals(4, inverseCaptcha("12131415"))
    }
}