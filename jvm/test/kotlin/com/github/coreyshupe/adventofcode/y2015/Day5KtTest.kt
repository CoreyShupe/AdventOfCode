package com.github.coreyshupe.adventofcode.y2015

import org.junit.Test

import org.junit.Assert.*

class Day5KtTest {

    @Test
    fun isNiceP1Test() {
        assert(isNiceP1("ugknbfddgicrmopn"))
        assert(isNiceP1("aaa"))
        assert(!isNiceP1("jchzalrnumimnmhp"))
        assert(!isNiceP1("haegwjzuvuyypxyu"))
        assert(!isNiceP1("dvszwmarrgswjxmb"))
    }

    @Test
    fun isNiceP2Test() {
        assert(isNiceP2("qjhvhtzxzqqjkmpb"))
        assert(isNiceP2("xxyxx"))
        assert(!isNiceP2("uurcxstgmygtbstg"))
        assert(!isNiceP2("ieodomkazucvgmuy"))
    }
}