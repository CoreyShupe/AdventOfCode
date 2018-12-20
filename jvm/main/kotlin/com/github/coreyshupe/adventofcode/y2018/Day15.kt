package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.*

fun main(args: Array<String>) {
    input(18, 15, ResourceType.Lined) {
        val loaded = loadEntities(it)
        determineFinalOutcome(loaded.first, loaded.second.clone(), 3).first.let(::println) // part 1
        findBestOutcome(loaded.first, loaded.second).let(::println) // part 2
    }
}

fun Set<Entity>.clone(): MutableSet<Entity> {
    val set = mutableSetOf<Entity>()
    forEach { set.add(it.clone()) }
    return set
}

fun findBestOutcome(tiles: Map<Pair<Int, Int>, TileType>, entities: MutableSet<Entity>): Int {
    var i = 4
    while (true) {
        val outcome = determineFinalOutcome(tiles, entities.clone(), i, true)
        if (outcome.second) return outcome.first
        i++
    }
}

fun determineFinalOutcome(
    tiles: Map<Pair<Int, Int>, TileType>,
    entities: MutableSet<Entity>,
    attackPower: Int,
    stopSuddenlyIfElfDies: Boolean = false
): Pair<Int, Boolean> {
    val entityComparator: Comparator<Entity> = Comparator { o1, o2 ->
        pairComparator.compare(o1.position.pointer, o2.position.pointer)
    }
    var elfDeath = false
    var round = 0
    while (true) {
        round++
        val elimed = mutableSetOf<Entity>()
        entities.toSortedSet(entityComparator)
            .forEach {
                if (elimed.contains(it)) return@forEach
                val enemies = when (it) {
                    is Entity.Goblin -> entities.filter { enemy -> enemy is Entity.Elf }
                    is Entity.Elf -> entities.filter { enemy -> enemy is Entity.Goblin }
                }
                if (enemies.isEmpty()) {
                    return (round - 1) * entities.sumBy { it.health.health } to !elfDeath
                }
                fun attack(easyTerminate: Boolean = false) {
                    val x1 = it.position.peek(-1)
                    val x2 = it.position.peek(1)
                    val y1 = it.position.peek(y = -1)
                    val y2 = it.position.peek(y = 1)
                    val valid = enemies.toMap({ e -> e.position.pointer }, { e -> e })
                        .filter { e -> e.key == x1 || e.key == x2 || e.key == y1 || e.key == y2 }
                    if (!valid.isEmpty()) {
                        // attack
                        val entity = valid.values.reduce { acc, entity ->
                            when {
                                acc.health.health == entity.health.health -> {
                                    when (entityComparator.compare(acc, entity)) {
                                        -1 -> acc
                                        1 -> entity
                                        else -> throw UnsupportedOperationException("Not supported.")
                                    }
                                }
                                acc.health.health > entity.health.health -> entity
                                else -> acc
                            }
                        }
                        if (entity.health.hit(if (it is Entity.Goblin) 3 else attackPower)) {
                            if (entity is Entity.Elf) elfDeath = true
                            entities.remove(entity)
                            elimed.add(entity)
                        }
                    } else {
                        if (easyTerminate) return
                        val enemyLocs = enemies.map(Entity::position).map(Pointer::pointer).toSet()
                        val friends = when (it) {
                            is Entity.Goblin -> entities.filter { enemy -> enemy is Entity.Goblin }
                            is Entity.Elf -> entities.filter { enemy -> enemy is Entity.Elf }
                        }.map(Entity::position).map(Pointer::pointer).toSet()
                        searchBFSEnemies(
                            it.position.pointer,
                            tiles.filter { x -> !friends.contains(x.key) && x.value == TileType.NORMAL }.keys.toMutableSet(),
                            enemyLocs
                        )?.let { x ->
                            it.position.reRef(x.start)
                        }
                        attack(true)
                    }
                }
                attack()
                if (elfDeath && stopSuddenlyIfElfDies) {
                    return 0 to false
                }
            }
    }
}

fun loadEntities(input: List<String>): Pair<Map<Pair<Int, Int>, TileType>, MutableSet<Entity>> {
    val map = mutableMapOf<Pair<Int, Int>, TileType>()
    val set = mutableSetOf<Entity>()
    input.forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            val loc = x to y
            map[x to y] = if (c == '#') TileType.WALL else TileType.NORMAL
            when (c) {
                'G' -> set.add(Entity.Goblin(loc))
                'E' -> set.add(Entity.Elf(loc))
            }
        }
    }
    return map to set
}

enum class TileType {
    NORMAL,
    WALL
}

private val pairComparator: Comparator<Pair<Int, Int>> = Comparator { o1, o2 ->
    when {
        o1.second < o2.second -> -1
        o2.second < o1.second -> 1
        o1.first < o2.first -> -1
        o2.first < o1.first -> 1
        else -> 0
    }
}

private fun searchBFSEnemies(
    initialPoint: Pair<Int, Int>,
    possibleLocations: MutableSet<Pair<Int, Int>>,
    enemies: Set<Pair<Int, Int>>
): Node? {
    val runningNodes = sortedSetOf<Node>(kotlin.Comparator { o1, o2 -> pairComparator.compare(o1.loc, o2.loc) })

    fun popAndPush(set: MutableSet<Node>, node: Node) {
        set.add(node)
        possibleLocations.remove(node.loc)
    }
    for (x in arrayOf(-1, 1)) {
        val first = Pair(initialPoint.first + x, initialPoint.second)
        val second = Pair(initialPoint.first, initialPoint.second + x)
        if (possibleLocations.contains(first)) popAndPush(runningNodes, Node(first, first))
        if (possibleLocations.contains(second)) popAndPush(runningNodes, Node(second, second))
    }
    while (!possibleLocations.isEmpty()) {
        val newNodes = mutableSetOf<Node>()
        runningNodes.forEach {
            for (x in arrayOf(-1, 1)) {
                val first = Pair(it.loc.first + x, it.loc.second)
                val second = Pair(it.loc.first, it.loc.second + x)
                if (enemies.contains(second)) return Node(it.start, second)
                if (enemies.contains(first)) return Node(it.start, second)
                if (possibleLocations.contains(first)) popAndPush(newNodes, Node(it.start, first))
                if (possibleLocations.contains(second)) popAndPush(newNodes, Node(it.start, second))
            }
        }
        if (newNodes.isEmpty()) return null
        runningNodes.clear()
        newNodes.forEach { runningNodes.add(it) }
    }
    return null
}

private data class Node(val start: Pair<Int, Int>, val loc: Pair<Int, Int>)

class Health(initialHealth: Int) {
    var health = initialHealth
        private set(value) {
            field = value
        }

    fun hit(amount: Int = 3): Boolean {
        health = if (amount >= health) 0 else health - amount
        return health == 0
    }
}

sealed class Entity(val position: Pointer, val health: Health = Health(200)) {
    data class Goblin(val iloc: Pair<Int, Int>) : Entity(Pointer(iloc)) {
        override fun clone() = Goblin(iloc)
    }

    data class Elf(val iloc: Pair<Int, Int>) : Entity(Pointer(iloc)) {
        override fun clone() = Elf(iloc)
    }

    abstract fun clone(): Entity
}