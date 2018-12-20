package com.github.coreyshupe.adventofcode

enum class Direction {
    EAST {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first + len, pointer.second)
        override fun move(left: Boolean) = if (left) NORTH else SOUTH
    },
    WEST {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first - len, pointer.second)
        override fun move(left: Boolean) = if (left) SOUTH else NORTH
    },
    NORTH {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first, pointer.second + len)
        override fun move(left: Boolean) = if (left) WEST else EAST
    },
    SOUTH {
        override fun apply(pointer: Pair<Int, Int>, len: Int) = Pair(pointer.first, pointer.second - len)
        override fun move(left: Boolean) = if (left) EAST else WEST
    };

    abstract fun apply(pointer: Pair<Int, Int>, len: Int = 1): Pair<Int, Int>
    abstract fun move(left: Boolean): Direction
}

class DirectionNode(initDirection: Direction) {
    var direction: Direction = initDirection
        private set(value) {
            field = value
        }

    fun move(left: Boolean): Direction {
        direction = direction.move(left)
        return direction
    }
}

class Pointer(initPointer: Pair<Int, Int>) {
    var pointer: Pair<Int, Int> = initPointer
        private set(value) {
            field = value
        }

    fun reRef(newLoc: Pair<Int, Int>) {
        pointer = newLoc
    }

    fun mutate(x: Int = 0, y: Int = 0): Pointer {
        reRef(pointer.first + x to pointer.second + y)
        return this
    }

    fun peek(x: Int = 0, y: Int = 0): Pair<Int, Int> {
        return pointer.first + x to pointer.second + y
    }

    fun applyDirection(node: DirectionNode, len: Int = 1) = applyDirection(node.direction, len)

    fun applyDirection(direction: Direction, len: Int = 1): Pair<Int, Int> {
        pointer = direction.apply(pointer, len)
        return pointer
    }
}

fun <T> Array<Array<T>>.at(pointer: Pointer) = at(pointer.pointer)
fun <T> Array<Array<T>>.setAt(pointer: Pointer, element: T) = setAt(pointer.pointer, element)
fun <T> Array<Array<T>>.at(pointer: Pair<Int, Int>) = get(pointer.first)[pointer.second]
fun <T> Array<Array<T>>.setAt(pointer: Pair<Int, Int>, element: T) {
    get(pointer.first)[pointer.second] = element
}