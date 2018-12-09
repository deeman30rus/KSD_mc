package com.delizarov.ksmartdiet.data.impl

import android.content.Context
import android.content.SharedPreferences
import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.data.db.*
import com.delizarov.ksmartdiet.domain.DietSettingsNotFoundException
import com.delizarov.ksmartdiet.domain.models.*
import io.reactivex.Observable
import org.joda.time.DateTime
import javax.inject.Inject

//todo удалить когда рецепты станут подконтрольны базе
val gRecipes = mapOf(
        1L to pancakes,
        2L to oatmeal,
        3L to friedEggs,
        4L to friedPotatoes,
        5L to lazies
)

class DietRepositoryImpl @Inject constructor(
        ctx: Context,
        val db: DietDB
) : DietRepository {
    private val recipes = gRecipes

    override fun readRation(): List<Ration> {

        return listOf()
    }

    override fun getCurrentRation(): Ration {

        return Ration(
                "Всеядный",
                "",
                recipes.values.toSet()
        )
    }

    private val preferences: SharedPreferences = ctx.getSharedPreferences(DIET_PREFERENCES, Context.MODE_PRIVATE)

    override fun getDietSettings(): Observable<DietSettings> = Observable.create {

        val planDays = preferences.getInt(DIET_SETTINGS_PLAN_DAYS, -1)

        if (planDays == -1)
            it.onError(DietSettingsNotFoundException())
        else {

            val mealTypes = db
                    .mealTypeDao()
                    .getMealTypes()
                    .map { it.kModel }
                    .toMutableList()

            val settings = DietSettings(mealTypes, planDays)

            it.onNext(settings)
        }

        it.onComplete()
    }


    override fun writeDietSettings(dietSettings: DietSettings) {

        preferences
                .edit()
                .putInt(DIET_SETTINGS_PLAN_DAYS, dietSettings.planDays)
                .apply()

        dietSettings.mealTypes.forEach {
            db.mealTypeDao().addMealType(it.dbEntity)
        }
    }

    override fun getMealsForDate(date: DateTime): List<Meal> = getMealsForPeriod(date, date, null)


    override fun getMealsForPeriod(dateFrom: DateTime, dateTo: DateTime, mealType: MealType?): List<Meal> {
        return if (mealType == null)
            db
                    .mealDao()
                    .getMeals(dateFrom, dateTo)
                    .map { it.kModel(recipes[it.recipeId]!!) }
                    .toList()
        else
            db
                    .mealDao()
                    .getMeals(dateFrom, dateTo, mealType.name)
                    .map {
                        it.kModel
                    }
    }

    override fun writeMeal(meal: Meal) {

        db.mealDao().addMeal(meal.dbEntity)
    }

    override fun updateMeal(meal: Meal) {

        db.mealDao().updateMeal(meal.dbEntity)
    }

    override fun clearDietData() {

        db.clearAllTables()
        preferences
                .edit()
                .clear()
                .apply()
    }

    companion object {

        private const val DIET_SETTINGS_PLAN_DAYS = "plan_days"

        private const val DIET_PREFERENCES = "diet_prefs"
    }

    override fun getRecipeById(id: Long) = gRecipes[id]!!
}