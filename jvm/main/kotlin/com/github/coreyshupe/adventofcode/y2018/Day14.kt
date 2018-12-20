package com.github.coreyshupe.adventofcode.y2018

import com.github.coreyshupe.adventofcode.ResourceType
import com.github.coreyshupe.adventofcode.getRealIndex
import com.github.coreyshupe.adventofcode.input

fun main(args: Array<String>) {
    input(18, 14, ResourceType.Full) {
        val input = it.toInt()
        findNextTenRecipes(input).let(::println) // part 1
        findRecipeCounter(it).let(::println) // part 2
    }
}

fun findNextTenRecipes(input: Int): String {
    val recipes = mutableListOf(3, 7)
    var p1 = 0
    var p2 = 1
    while (recipes.size < input + 10) {
        val next = recipes[p1] + recipes[p2]
        if (next >= 10) {
            recipes.add(next / 10)
            recipes.add(next % 10)
        } else recipes.add(next)
        p1 = getRealIndex(recipes, p1 + 1 + recipes[p1])
        p2 = getRealIndex(recipes, p2 + 1 + recipes[p2])
    }
    return recipes.subList(recipes.size - 10, recipes.size).joinToString("")
}

fun findRecipeCounter(input: String): Int {
    val recipes = mutableListOf(3, 7)
    val array = Array(input.length) { ' ' }
    fun addRecipe(recipe: Int): Boolean {
        recipes.add(recipe)
        for (x in 0.until(input.length - 1)) {
            array[x] = array[x + 1]
        }
        array[input.length - 1] = "$recipe"[0]
        return array.joinToString("") == input
    }

    var p1 = 0
    var p2 = 1
    while (true) {
        val next = recipes[p1] + recipes[p2]
        if (next >= 10) {
            if (addRecipe(next / 10)) return recipes.size - input.length
            if (addRecipe(next % 10)) return recipes.size - input.length
        } else if (addRecipe(next)) return recipes.size - input.length
        p1 = getRealIndex(recipes, p1 + 1 + recipes[p1])
        p2 = getRealIndex(recipes, p2 + 1 + recipes[p2])
    }
}