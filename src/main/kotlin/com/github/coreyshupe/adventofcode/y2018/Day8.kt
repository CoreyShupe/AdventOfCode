package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.Stack
import com.github.coreyshupe.adventofcode.asResource
import com.github.coreyshupe.adventofcode.stackFromIterable

fun main(args: Array<String>) {
    "/2018/day8_input.txt".asResource(ResourceType.SpaceSplit) {
        println(countMetaDataStats(it)) // part 1
        println(countRootNodeData(it)) // part 2
    }
}

fun countMetaDataStats(input: List<String>) = genStack(input).let { countSum(it.pop(), it.pop(), it) }

fun countRootNodeData(input: List<String>) = genStack(input).let { findNodeValue(it.pop(), it.pop(), it) }

private fun findNodeValue(depth: Int, entries: Int, stack: Stack<Int>): Int = when (depth) {
    0 -> (1..entries).sumBy { stack.pop() }
    else -> (1..depth).mapIndexed { i, _ -> i + 1 to findNodeValue(stack.pop(), stack.pop(), stack) }.toMap().let {
        (1..entries).sumBy { _ -> it.getOrDefault(stack.pop(), 0) }
    }
}

private fun countSum(depth: Int, entries: Int, stack: Stack<Int>): Int = when (depth) {
    0 -> (1..entries).sumBy { stack.pop() }
    else -> (1..depth).sumBy { countSum(stack.pop(), stack.pop(), stack) } + (1..entries).sumBy { stack.pop() }
}

private fun genStack(input: List<String>) = stackFromIterable(input.map { it.toInt() })