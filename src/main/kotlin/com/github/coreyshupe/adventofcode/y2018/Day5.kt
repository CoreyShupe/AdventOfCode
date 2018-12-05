package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.asResource

fun main(args: Array<String>) {
    "/2018/day5_input.txt".asResource(ResourceType.Full) {
        println(countFromReducedPolymer(it)) // part 1
        println(countFromBetterReducedPolymer(it)) // part 2
    }
}

fun countFromReducedPolymer(input: String) = reactPolymersStack(input).length

fun countFromBetterReducedPolymer(input: String) =
    reactPolymersStack(input).let { "abcdefghijklmnopqrstuvwxyz".map { c -> reactPolymersStack(it, c).length }.min()!! }

private fun reactPolymersStack(input: String, ignore: Char = ' '): String {
    val ignore2 = changeCase(ignore)
    val stack = Stack()
    for (x in input) {
        if (x == ignore || x == ignore2) continue
        else if (!stack.isEmpty() && stack.peek()!! == changeCase(x)) stack.pop()
        else stack.push(x)
    }
    return stack.toString()
}

private class Stack {
    private var currentNode: Node? = null

    fun peek() = currentNode?.c
    fun isEmpty() = currentNode == null
    fun pop() {
        if (currentNode == null) throw IllegalStateException("Cannot pop empty stack.")
        currentNode = currentNode!!.node
    }

    fun push(element: Char) {
        currentNode = Node(element, currentNode)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var lastNode = currentNode
        while (lastNode != null) {
            builder.append(lastNode.c)
            lastNode = lastNode.node
        }
        return builder.toString()
    }
}

private data class Node(val c: Char, val node: Node?)

private fun changeCase(c: Char) = if (c.isUpperCase()) Character.toLowerCase(c) else Character.toUpperCase(c)