package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource
import java.lang.StringBuilder

fun main(args: Array<String>) {
    "/2018/day5_input.txt".asResource(ResourceType.Full) {
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

private fun reactPolymersStack(input: String, ignore: Char = ' '): Stack {
    val ignore2 = ignore.changeCase()
    val stack = Stack()
    for (x in input) {
        if (x == ignore || x == ignore2) continue
        else if (!stack.isEmpty && stack.currentNode!!.c == x.changeCase()) stack.pop()
        else stack.push(x)
    }
    return stack
}

private class Stack {
    var currentNode: Node? = null
    var size = 0
    val isEmpty get() = currentNode == null

    fun pop() {
        if (isEmpty) throw IllegalStateException("Cannot pop empty stack.")
        currentNode = currentNode?.node
        size--
    }

    fun push(element: Char) {
        currentNode = Node(element, currentNode)
        size++
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var x = currentNode
        while (x != null) {
            builder.append(x.c)
            x = x.node
        }
        return builder.toString()
    }
}

private data class Node(val c: Char, val node: Node?)

private fun Char.changeCase() = if (isUpperCase()) toLowerCase() else toUpperCase()