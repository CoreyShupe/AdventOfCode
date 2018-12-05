package com.github.coreyshupe.adventofcode.y2015

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import java.math.BigInteger
import java.security.MessageDigest

fun main(args: Array<String>) {
    "/2015/day4_input.txt".asResource(ResourceType.Full) {
        println(findHashWithLeadingZeroes(it, 5)) // part 1
        println(findHashWithLeadingZeroes(it, 6)) // part 2
    }
}

fun findHashWithLeadingZeroes(input: String, repeat: Int): Int {
    var x = 1
    while (true) {
        if ("$input$x".md5().startsWith("0".repeat(repeat))) return x
        x++
    }
}

private fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')