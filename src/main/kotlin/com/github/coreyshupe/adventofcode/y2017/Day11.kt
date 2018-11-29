package com.github.coreyshupe.adventofcode.y2017

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

val locationModifiers = mapOf<String, (location: Location) -> Unit>(
    Pair("n", { location -> location changeY 2 }),
    Pair("ne", { location -> location changeY 1; location changeX 1 }),
    Pair("se", { location -> location changeY -1; location changeX 1 }),
    Pair("s", { location -> location changeY -2 }),
    Pair("sw", { location -> location changeY -1; location changeX -1 }),
    Pair("nw", { location -> location changeY 1; location changeX -1 })
)

class Location {
    var bestDistance = 0
    var x = 0 // east+ west-
    var y = 0 // north+ south-

    infix fun changeX(change: Int) {
        x += change
        bestDistance = max(bestDistance, determineDistance(x, y))
    }

    infix fun changeY(change: Int) {
        y += change
        bestDistance = max(bestDistance, determineDistance(x, y))
    }
}

fun stepsFromPath(path: String): Pair<Int, Int> {
    val location = Location()
    path.split(',').map { locationModifiers[it] }.forEach { it?.invoke(location) }
    return Pair(determineDistance(location.x, location.y), location.bestDistance)
}

fun determineDistance(x: Int, y: Int): Int {
    val absX = abs(x)
    val absY = abs(y)
    val sub = min(absX, absY)
    return sub + (max(absX - sub, absY - sub) / 2)
}