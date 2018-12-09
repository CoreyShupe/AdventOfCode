package com.github.coreyshupe.adventofcode

class Stack<T> {
    private var currentNode: Node<T>? = null
    private var depth = 0
    val size get() = depth
    val isEmpty get() = currentNode == null

    fun pop(): T {
        if (isEmpty) throw IllegalStateException("Cannot pop empty stack.")
        val last = currentNode!!
        currentNode = currentNode?.node
        depth--
        return last.element
    }

    fun peek(): T? = currentNode?.element

    fun push(element: T) {
        currentNode = Node(element, currentNode)
        depth++
    }

    override fun toString() = buildString {
        var x = currentNode
        while (x != null) {
            append(x.element)
            x = x.node
        }
    }
}

data class Node<T>(val element: T, val node: Node<T>?)

fun <T> stackFromIterable(input: Iterable<T>): Stack<T> {
    val stack = Stack<T>()
    input.reversed().forEach { stack.push(it) }
    return stack
}