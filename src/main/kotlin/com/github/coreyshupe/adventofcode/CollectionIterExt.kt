package com.github.coreyshupe.adventofcode

fun <E> List<E>.getRealIndex(size: Int, index: Int) = index % size

fun <E, K, V> Collection<E>.toMap(eToK: (E) -> K, eToV: (E) -> V) =
    this.map { eToK.invoke(it) to eToV.invoke(it) }.toMap()

fun <E, R> Collection<E>.group(grouper: (E) -> R): MutableMap<R, MutableList<E>> {
    val map = mutableMapOf<R, MutableList<E>>()
    this.forEach {
        val get = grouper.invoke(it)
        if (!map.containsKey(get)) map[get] = mutableListOf()
        map[get]!!.add(it)
    }
    return map
}

fun <E> Collection<E>.findRepeated(): E? {
    val set = mutableSetOf<E>()
    return this.find { !set.add(it) }
}