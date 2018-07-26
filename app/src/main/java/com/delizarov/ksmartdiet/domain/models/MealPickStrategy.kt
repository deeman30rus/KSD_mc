package com.delizarov.ksmartdiet.domain.models

import com.delizarov.common.x.pickRandom

interface MealPickStrategy {

    fun pickMeal(ration: Ration, meals: List<Meal>): Recipe
}

sealed class DiversityStrategy : MealPickStrategy {

    override fun pickMeal(ration: Ration, meals: List<Meal>): Recipe {

        val seq = ration
                .recipes
                .asSequence()
                .map {
                    RecipeRate(it, 0)
                }
                .sortedByDescending { it.rate }

        val maxRate = seq.maxBy { it.rate }?.rate ?: 0

        return seq.filter {
            it.rate == maxRate
        }.toList()
                .pickRandom()
                .recipe
    }
}

class RecipeRate(
        val recipe: Recipe,
        val rate: Int
)