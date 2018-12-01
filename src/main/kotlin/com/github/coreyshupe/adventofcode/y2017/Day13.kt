package com.github.coreyshupe.adventofcode.y2017

data class Layer(val depth: Int, val index: Int, private val range: Int = (depth - 1) * 2) {
    fun caughtAt(time: Int) = (time + index) % range == 0
}

fun predictSeverity(input: String) = genFirewall(input).filter { it.caughtAt(0) }.map { it.index * it.depth }.sum()

fun findNecessaryDelay(input: String): Int {
    val layers = genFirewall(input)
    return generateSequence(0, Int::inc).filter {
        layers.none { layer -> layer.caughtAt(it) }
    }.first()
}

fun genFirewall(input: String): List<Layer> {
    val layerMap = input.split('\n').map {
        val split = it.split(": ")
        split[0].toInt() to split[1].toInt()
    }.toMap()
    return (0..(layerMap.maxBy { it.key }!!.key))
        .toList()
        .mapNotNull { if (layerMap.containsKey(it)) Layer(layerMap[it]!!, it) else null }
}