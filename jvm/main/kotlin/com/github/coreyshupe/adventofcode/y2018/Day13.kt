package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.*

fun main(args: Array<String>) {
    input(18, 13, ResourceType.Full) {
        val trackInfo = parseTracks(it)
        findFirstCrash(trackInfo.first, trackInfo.second).let(::println) // part 1
        findLastRemaining(trackInfo.first, trackInfo.second).let(::println) // part 2
    }
}

fun findFirstCrash(tracks: Map<Pair<Int, Int>, TrackType>, trains: Set<Train>): Pair<Int, Int> {
    while (true) {
        trains.sortedWith(Comparator { o1, o2 ->
            val imp1 = o1.pointer.pointer
            val imp2 = o2.pointer.pointer
            when {
                imp1.first < imp2.first -> -1
                imp2.first < imp1.first -> 1
                imp1.second < imp2.second -> -1
                imp2.second < imp1.second -> 1
                else -> 0
            }
        }).forEach { train ->
            val moved = train.move(tracks)
            if (trains.filter { it != train }.map(Train::pointer).map(Pointer::pointer).contains(moved)) {
                return train.pointer.pointer
            }
        }
    }
}

fun findLastRemaining(tracks: Map<Pair<Int, Int>, TrackType>, trains: Set<Train>): Pair<Int, Int> {
    val betterTrains = trains.toMutableSet()
    while (true) {
        val removeable = mutableSetOf<Pair<Int, Int>>()
        betterTrains.sortedWith(Comparator { o1, o2 ->
            val imp1 = o1.pointer.pointer
            val imp2 = o2.pointer.pointer
            when {
                imp1.first < imp2.first -> -1
                imp2.first < imp1.first -> 1
                imp1.second < imp2.second -> -1
                imp2.second < imp1.second -> 1
                else -> 0
            }
        }).forEach { train ->
            if (removeable.contains(train.pointer.pointer)) return@forEach
            val moved = train.move(tracks)
            if (betterTrains.filter { it != train }.map(Train::pointer).map(Pointer::pointer).contains(moved)) {
                removeable.add(moved)
            }
        }
        betterTrains.removeAll { removeable.contains(it.pointer.pointer) }
        if (betterTrains.size == 1) return betterTrains.first().pointer.pointer
    }
}

fun parseTracks(input: String): Pair<Map<Pair<Int, Int>, TrackType>, Set<Train>> {
    val map = mutableMapOf<Pair<Int, Int>, TrackType>()
    val set = mutableSetOf<Train>()
    input.split('\n').forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            val loc = x to y
            when (c) {
                '^', 'v', '<', '>', '-', '|' -> map[loc] = TrackType.NORMAL
            }
            when (c) {
                '/' -> map[loc] = TrackType.TURN_RIGHT
                '\\' -> map[loc] = TrackType.TURN_LEFT
                '>' -> set.add(Train(DirectionNode(Direction.EAST), Pointer(loc)))
                'v' -> set.add(Train(DirectionNode(Direction.NORTH), Pointer(loc)))
                '^' -> set.add(Train(DirectionNode(Direction.SOUTH), Pointer(loc)))
                '<' -> set.add(Train(DirectionNode(Direction.WEST), Pointer(loc)))
                '+' -> map[loc] = TrackType.INTERSECTION
            }
        }
    }
    return map to set
}

enum class TrackType {
    NORMAL,
    TURN_LEFT,
    TURN_RIGHT,
    INTERSECTION
}

class TurnState {
    private var state = 0
        private set(value) {
            field = if (value == 3) 0 else value
        }

    fun turn(): Int {
        val tmp = state
        state += 1
        return tmp
    }
}

data class Train(val direction: DirectionNode, val pointer: Pointer, val turnState: TurnState = TurnState()) {
    fun move(tracks: Map<Pair<Int, Int>, TrackType>): Pair<Int, Int> {
        val on = tracks[pointer.pointer]!!
        when (on) {
            TrackType.NORMAL -> pointer.applyDirection(direction)
            TrackType.TURN_LEFT -> move(true)
            TrackType.TURN_RIGHT -> move(false)
            TrackType.INTERSECTION -> when (turnState.turn()) {
                0 -> pointer.applyDirection(direction.move(false))
                1 -> pointer.applyDirection(direction)
                2 -> pointer.applyDirection(direction.move(true))
            }
        }
        return pointer.pointer
    }

    private fun move(left: Boolean) {
        when (direction.direction) {
            Direction.WEST, Direction.EAST -> pointer.applyDirection(direction.move(left))
            Direction.NORTH, Direction.SOUTH -> pointer.applyDirection(direction.move(!left))
        }
    }
}