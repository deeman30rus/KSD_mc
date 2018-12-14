package com.delizarov.ksmartdiet.domain.models

import com.delizarov.common.x.pickRandom
import javax.inject.Inject

/**
 * Интерфейс для выбора рецептов
 * */
interface RecipePickStrategy {

    /**
     * Выбирает рецепт на основе, указанного рациона, предыдущих блюд
     *
     * @param ration выбранный рацион
     * @param prevMeals блюда предыдущих дней
     * @param excludedRecipes рецепты, которые исключены из рациона
     * */
    fun pickRecipe(ration: Ration, prevMeals: List<Meal>, excludedRecipes: List<Recipe>): Recipe
}

class DiversityStrategy @Inject constructor() : RecipePickStrategy {

    override fun pickRecipe(ration: Ration, prevMeals: List<Meal>, excludedRecipes: List<Recipe>): Recipe {

        val tags = prevMeals
                .asSequence()
                .flatMap {
                    it.recipe.tags.asSequence()
                }.toList()

        val candidates = ration
                .recipes
                .asSequence()
                .filter { it !in excludedRecipes }
                .map {
                    RecipeRate(it, tags.intersect(it.tags).size)
                }
                .sortedBy { it.rate }
                .toList()

        val maxRate = if (candidates.isNotEmpty()) candidates[0].rate else 0

        return candidates.filter {
            it.rate == maxRate
        }.toList()
                .pickRandom()
                .recipe
    }
}

private class RecipeRate(
        val recipe: Recipe,
        val rate: Int
)