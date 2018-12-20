package com.github.coreyshupe.adventofcode.y2018

import org.junit.Test

import org.junit.Assert.*

class Day15KtTest {

    @Test
    fun determineFinalOutcomeTest() {
        val inputs = arrayOf(
            """#######
#G..#E#
#E#E.E#
#G.##.#
#...#E#
#...E.#
#######""", """#######
#E..EG#
#.#G.E#
#E.##E#
#G..#.#
#..E#.#
#######""", """#######
#E.G#.#
#.#G..#
#G.#.G#
#G..#.#
#...E.#
#######""", """#######
#.E...#
#.#..G#
#.###.#
#E#G#G#
#...#G#
#######""", """#########
#G......#
#.E.#...#
#..##..G#
#...##..#
#...#...#
#.G...G.#
#.....G.#
#########"""
        ).map { it.split('\n') }.map(::loadEntities)

        fun expect(x: Int, index: Int) {
            assertEquals(x, determineFinalOutcome(inputs[index].first, inputs[index].second.clone(), 3).first)
        }

        expect(36334, 0)
        expect(39514, 1)
        expect(27755, 2)
        expect(28944, 3)
        expect(18740, 4)
    }

    @Test
    fun findBestOutcomeTest() {
        val inputs = arrayOf(
            """#######
#.G...#
#...EG#
#.#.#G#
#..G#E#
#.....#
#######""", """#######
#E..EG#
#.#G.E#
#E.##E#
#G..#.#
#..E#.#
#######""", """#######
#E.G#.#
#.#G..#
#G.#.G#
#G..#.#
#...E.#
#######""", """#######
#.E...#
#.#..G#
#.###.#
#E#G#G#
#...#G#
#######""", """#########
#G......#
#.E.#...#
#..##..G#
#...##..#
#...#...#
#.G...G.#
#.....G.#
#########"""
        ).map { it.split('\n') }.map(::loadEntities)

        fun expect(x: Int, index: Int) {
            assertEquals(x, findBestOutcome(inputs[index].first, inputs[index].second))
        }

        expect(4988, 0)
        expect(31284, 1)
        expect(3478, 2)
        expect(6474, 3)
        expect(1140, 4)
    }
}