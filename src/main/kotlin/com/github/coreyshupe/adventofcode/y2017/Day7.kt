package com.github.coreyshupe.adventofcode.y2017

import java.lang.IllegalArgumentException

fun towerTop(towerInputs: String) = findTop(genList(towerInputs))

data class Tower(val name: String, val weight: Int, val towers: List<Tower>?)

data class Weights(val originalWeight: Int, val weight: Int, val weights: List<Weights>?)

fun watchTowerWeight(towerInputs: String): Any {
    val list = genList(towerInputs)
    val tower = generateTower(findTop(list), list.toMap())
    val weights = mapCalculatedWeights(tower)
    fun List<Weights>.allMatch(): Boolean {
        val first = this.first()
        return !(this.any { first.weight != it.weight })
    }

    fun List<Weights>.mapWeights(): Collection<List<Weights>> {
        val mapped = mutableMapOf<Int, MutableList<Weights>>()
        for (weight in this) {
            if (mapped.containsKey(weight.weight)) {
                mapped[weight.weight]!!.add(weight)
            } else {
                mapped[weight.weight] = mutableListOf(weight)
            }
        }
        return mapped.values
    }

    fun List<Weights>.findDifferent() = mapWeights().reduce { x, z -> if (x.size > z.size) z else x }.first()
    fun List<Weights>.findMajor() = mapWeights().reduce { x, z -> if (x.size > z.size) x else z }.first()
    val weightArray = arrayOf(null, weights)
    while (weightArray[1]!!.weights != null && !weightArray[1]!!.weights!!.allMatch()) {
        val next = weightArray[1]!!.weights!!.findDifferent()
        weightArray[0] = weightArray[1]
        weightArray[1] = next
    }
    val watchedWeight = weightArray[1]!!
    val originalWeight = watchedWeight.originalWeight
    val totalRest = watchedWeight.weights?.sumBy { it.weight } ?: 0
    val needed = weightArray[0]!!.weights!!.findMajor()
    return ((needed.weight - totalRest) - originalWeight) + originalWeight
}

fun mapCalculatedWeights(tower: Tower): Weights {
    if (tower.towers == null) return Weights(tower.weight, tower.weight, null)
    val weights = tower.towers.map { mapCalculatedWeights(it) }
    return Weights(tower.weight, weights.sumBy { it.weight } + tower.weight, weights)
}

fun genList(towerInputs: String): List<Pair<String, Pair<Int, List<String>?>>> =
    towerInputs.split('\n').map {
        val splitTower = it.replace(",", "").split(' ')
        val item = splitTower[0]
        val items = if (splitTower.size == 2) null else splitTower.subList(3, splitTower.size)
        val weight = splitTower[1]
        item to Pair(weight.substring(1, weight.length - 1).toInt(), items)
    }

fun findTop(list: List<Pair<String, Pair<Int, List<String>?>>>): String {
    val nullFiltered = list.filter { it.second.second != null }
    return nullFiltered.find {
        !nullFiltered.map { x -> x.second.second!! }.any { arr -> arr.contains(it.first) }
    }!!.first
}

fun generateTower(name: String, map: Map<String, Pair<Int, List<String>?>>): Tower {
    if (!map.containsKey(name)) throw IllegalArgumentException("Could not find tower base $name.")
    val info = map[name]!!
    if (info.second == null) {
        return Tower(name, info.first, null)
    }
    return Tower(name, info.first, generateTowers(info.second!!, map))
}

fun generateTowers(groups: List<String>, map: Map<String, Pair<Int, List<String>?>>) =
    groups.map { generateTower(it, map) }