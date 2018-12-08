package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.Stack
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 5, ResourceType.Full) {
        println(countFromReducedPolymer(it)) // part 1
        println(countFromBetterReducedPolymer(it)) // part 2
    }
}

fun countFromReducedPolymer(input: String) = reactPolymersStack(input).size

fun countFromBetterReducedPolymer(input: String) =
    reactPolymersStack(input).toString().let {
        ('a'..'z').map { c ->
            reactPolymersStack(
                it,
                c
            ).size
        }.min()!!
    }

private fun reactPolymersStack(input: String, ignore: Char = ' '): Stack<Char> {
    val ignore2 = ignore.changeCase()
    val stack = Stack<Char>()
    for (x in input) {
        if (x == ignore || x == ignore2) continue
        else if (!stack.isEmpty && stack.currentNode!!.element == x.changeCase()) stack.pop()
        else stack.push(x)
    }
    return stack
}

private fun Char.changeCase() = if (isUpperCase()) toLowerCase() else toUpperCase()