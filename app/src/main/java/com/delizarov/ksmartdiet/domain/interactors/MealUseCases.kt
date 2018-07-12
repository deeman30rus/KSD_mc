package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.common.x.pickRandom
import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.domain.models.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import javax.inject.Inject

sealed class MealReadParams

class DateParams(
        val date: DateTime,
        val settings: DietSettings
) : MealReadParams()

class GetMealUseCase @Inject constructor(
        private val dietRepository: DietRepository
) : UseCase<Meal, MealReadParams>() {

    override fun createObservable(params: MealReadParams?): Observable<Meal> =
            mealsObservable(params)
                    .subscribeOn(Schedulers.newThread())

    private fun mealsObservable(params: MealReadParams?) =
            when (params) {
                is DateParams ->
                    Observable.fromIterable(mealsForDate(params.date, params.settings))
                else -> Observable.empty<Meal>()
            }

    private fun mealsForDate(date: DateTime, settings: DietSettings): List<Meal> {

        val meals = dietRepository.getMealsForDate(date)

        if (meals.size == settings.mealTypes.size)
            return meals

        val notContained = meals.filter { it.type !in settings.mealTypes }.map(Meal::type).toList()

        val mutableMeals = meals.toMutableList()

        for (type in notContained) {
            val meal = createMeal(date, type)

            dietRepository.writeMeal(meal)

            mutableMeals.add(meal)
        }

        return mutableMeals

    }

    private fun createMeal(date: DateTime, type: MealType): Meal {

        val dateFrom = date.minusDays(3).withTimeAtStartOfDay()
        val dateTo = date.plusDays(1).withTimeAtStartOfDay()

        val prevMeals = dietRepository.getMealsForPeriod(dateFrom, dateTo, type)

        val ration = dietRepository.getCurrentRation()

        val recipe = decide(ration, prevMeals)

        return Meal(
                type,
                recipe,
                date
        )

    }

    private fun decide(ration: Ration, prevMeals: List<Meal>): Recipe = ration.recipes.pickRandom()
}