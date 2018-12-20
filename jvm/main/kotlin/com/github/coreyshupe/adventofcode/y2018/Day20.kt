package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.Pointer
import java.io.InputStream

fun main(args: Array<String>) =
    with(String::class.java.getResourceAsStream("/2018/day20_input")) { determineRouteData(this).let(::println) }

fun determineRouteData(stream: InputStream): Pair<Int, Int> {
    val map = mutableMapOf((0 to 0) to 0)
    read(stream, mutableSetOf(Branch(0 to 0, 0)), map)
    return map.maxBy { it.value }!!.value /*part 1*/ to map.count { it.value >= 1000 } /*part 2*/
}

private fun Set<Branch>.mutateAll(with: Pair<Int, Int>, pathMap: MutableMap<Pair<Int, Int>, Int>) {
    forEach {
        val pair = it.inc(with)
        if (pathMap.containsKey(pair.first)) {
            if (pathMap[pair.first]!! < pair.second) pathMap[pair.first] = pair.second
        } else {
            pathMap[pair.first] = pair.second
        }
    }
}

fun read(stream: InputStream, branches: MutableSet<Branch>, pathMap: MutableMap<Pair<Int, Int>, Int>) {
    val currentBranch = mutableSetOf<Branch>()
    val fixedBranches = mutableSetOf<Branch>()
    currentBranch.addAll(branches.map(Branch::clone))
    while (true) {
        val c = stream.read()
        if (c == -1) break
        if (when (c.toChar()) {
                '^', '$' -> false // ignore wrappings
                'E' -> {
                    currentBranch.mutateAll(1 to 0, pathMap)
                    false
                }
                'N' -> {
                    currentBranch.mutateAll(0 to 1, pathMap)
                    false
                }
                'S' -> {
                    currentBranch.mutateAll(0 to -1, pathMap)
                    false
                }
                'W' -> {
                    currentBranch.mutateAll(-1 to 0, pathMap)
                    false
                }
                '|' -> {
                    fixedBranches.addAll(currentBranch)
                    currentBranch.clear()
                    currentBranch.addAll(branches.map(Branch::clone))
                    false
                }
                ')' -> true
                '(' -> {
                    read(stream, currentBranch, pathMap)
                    false
                }
                else -> true
            }
        ) break
    }
    branches.clear()
    branches.addAll(fixedBranches)
}

class Branch(initPosition: Pair<Int, Int>, initDistance: Int) {
    private val pointer = Pointer(initPosition)
    private var distance = initDistance
    private val visited = mutableMapOf<Pair<Int, Int>, Int>()

    fun inc(mutatable: Pair<Int, Int>): Pair<Pair<Int, Int>, Int> {
        distance++
        pointer.mutate(mutatable.first, mutatable.second)
        return if (visited.containsKey(pointer.pointer)) {
            distance = visited[pointer.pointer]!!
            (0 to 0) to 0
        } else {
            visited[pointer.pointer] = distance
            pointer.pointer to distance
        }
    }

    fun clone(): Branch {
        val branch = Branch(pointer.pointer, distance)
        branch.visited.putAll(visited)
        return branch
    }
}