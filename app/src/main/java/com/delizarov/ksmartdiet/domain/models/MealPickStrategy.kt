package com.delizarov.ksmartdiet.domain.models

import com.delizarov.common.x.pickRandom
import javax.inject.Inject

interface MealPickStrategy {

    fun pickMeal(ration: Ration, meals: List<Meal>): Recipe
}

class DiversityStrategy @Inject constructor() : MealPickStrategy {

    override fun pickMeal(ration: Ration, meals: List<Meal>): Recipe {

        val tags = meals
                .asSequence()
                .flatMap {
                    it.recipe.tags.asSequence()
                }.toList()

        val seq = ration
                .recipes
                .asSequence()
                .map {
                    RecipeRate(it, tags.intersect(it.tags).size)
                }
                .sortedBy { it.rate }

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