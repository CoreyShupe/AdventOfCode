package com.github.coreyshupe.adventofcode.y2018

import org.junit.Assert.assertEquals
import org.junit.Test

class Day12KtTest {

    @Test
    fun findActivePotsTest() {
        // had to use these inputs, the examples given didn't really cover anything unit-testable
        // although these answers were confirmed by AoC
        """initial state: #....#.#....#....#######..##....###.##....##.#.#.##...##.##.#...#..###....#.#...##.###.##.###...#..#

#..#. => #
#...# => #
.##.# => #
#.... => .
..#.. => .
#.##. => .
##... => #
##.#. => #
.#.## => #
.#.#. => .
###.. => .
#..## => .
###.# => .
...## => .
#.#.. => #
..... => .
##### => #
..### => .
..#.# => #
....# => .
...#. => #
####. => #
.#... => #
#.#.# => #
.##.. => #
..##. => .
##..# => .
.#..# => #
##.## => #
.#### => .
.###. => #
#.### => .""".trimIndent().split('\n').apply {
            val initial = this[0].split(' ')[2]
            assertEquals(3915, findActivePots(initial, drop(2), 20))
            assertEquals(4900000001793, findActivePots(initial, drop(2), 50_000_000_000))
        }
    }
}