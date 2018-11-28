package com.github.coreyshupe.adventofcode.y2017

fun checkPassphrase(passphrase: String, ignoreLocation: Boolean = false): Boolean {
    val words = passphrase.split(" ")
    return checkPassphrase(if (ignoreLocation) words.map { it.toMutableList().sorted().joinToString("") } else words)
}

fun checkPassphrase(words: List<String>) = words.groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty()