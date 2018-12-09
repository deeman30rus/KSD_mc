package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.domain.models.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import javax.inject.Inject

private fun pickUpRecipe(dietRepository: DietRepository, strategy: RecipePickStrategy, date: DateTime, type: MealType, excludedRecipes: List<Recipe>): Recipe {
    val dateFrom = date.minusDays(3).withTimeAtStartOfDay()
    val dateTo = date.plusDays(1).withTimeAtStartOfDay()

    val prevMeals = dietRepository.getMealsForPeriod(dateFrom, dateTo, type)

    val ration = dietRepository.getCurrentRation()

    return strategy.pickRecipe(ration, prevMeals, excludedRecipes)
}

sealed class MealReadParams

class DateParams(
        val date: DateTime,
        val settings: DietSettings
) : MealReadParams()

/**
 * Сценарий использования, для получения приёмов пищи
 * */
class GetMealUseCase @Inject constructor(
        private val dietRepository: DietRepository,
        private val strategy: RecipePickStrategy
) : UseCase<Meal, MealReadParams>() {

    override fun createObservable(params: MealReadParams?): Observable<Meal> =
            mealsObservable(params)
                    .subscribeOn(Schedulers.newThread())

    private fun mealsObservable(params: MealReadParams?) =
            when (params) {
                is DateParams ->
                    Observable.defer { Observable.fromIterable(mealsForDate(params.date, params.settings)) }
                else -> Observable.empty<Meal>()
            }.subscribeOn(Schedulers.newThread())

    private fun mealsForDate(date: DateTime, settings: DietSettings): List<Meal> {

        val meals = dietRepository.getMealsForDate(date)

        if (meals.size == settings.mealTypes.size)
            return meals

        val presented = meals
                .map { it.type }
                .toSet()

        val notContained = settings
                .mealTypes
                .filter { it !in presented }
                .toList()

        val mutableMeals = meals.toMutableList()

        for (type in notContained) {
            val meal = pickUpMeal(date, type)

            dietRepository.writeMeal(meal)

            mutableMeals.add(meal)
        }

        return mutableMeals

    }

    private fun pickUpMeal(date: DateTime, type: MealType) = Meal(
            type,
            pickUpRecipe(dietRepository, strategy, date, type, emptyList()),
            date
    )
}

class SuggestMealUseCase @Inject constructor(
        private val dietRepository: DietRepository,
        private val strategy: RecipePickStrategy
) : UseCase<Meal, Meal>() {

    override fun createObservable(params: Meal?): Observable<Meal> =
            mealObservable(params)
                    .subscribeOn(Schedulers.newThread())

    private fun mealObservable(selectedMeal: Meal?): Observable<Meal> {

        if (selectedMeal == null)
            return Observable.empty<Meal>()

        return Observable.fromCallable {

            var r = pickUpRecipe(dietRepository, strategy, selectedMeal.date, selectedMeal.type, listOf(selectedMeal.recipe))
            val meal = Meal(
                    selectedMeal.type,
                    r,
                    selectedMeal.date
            )

            dietRepository.updateMeal(meal)

            meal
        }
    }

}