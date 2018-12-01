package com.github.coreyshupe.adventofcode.y2017

fun generator(initial: Long, factor: Long, divis: Long = 2147483647) =
    generateSequence((initial * factor) % divis) {
        (it * factor) % divis
    }.map { it.toShort() }

fun countGeneratorConnections(aInitial: Long, bInitial: Long): Int {
    val genA = generator(aInitial, 16807)
    val genB = generator(bInitial, 48271)
    return countGens(genA, genB, 40_000_000)
}

fun countGeneratorConnectionsIndependently(aInitial: Long, bInitial: Long): Int {
    val genA = generator(aInitial, 16807).filter { it % 4 == 0 }
    val genB = generator(bInitial, 48271).filter { it % 8 == 0 }
    return countGens(genA, genB, 5_000_000)
}

fun countGens(gen1: Sequence<Short>, gen2: Sequence<Short>, count: Int): Int {
    return gen1.zip(gen2).take(count).count { it.first == it.second }
}