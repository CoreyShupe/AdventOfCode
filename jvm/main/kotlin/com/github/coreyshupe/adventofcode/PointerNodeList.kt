package com.github.coreyshupe.adventofcode

// Thanks Plastic <3
class PointerNodeList<T>(initial: T) {
    private var previous = this
    private var next = this
    private var etc = initial
    val back get() = previous
    val forward get() = next
    val value get() = etc

    fun insert(value: T): PointerNodeList<T> {
        val new = PointerNodeList(value)
        new.next = next
        next.previous = new
        next = new
        new.previous = this
        return new
    }

    fun kill(): T {
        next.previous = previous
        previous.next = next
        return value
    }
}