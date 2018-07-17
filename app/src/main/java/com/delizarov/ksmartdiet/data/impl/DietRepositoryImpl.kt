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


class DietRepositoryImpl @Inject constructor(
        ctx: Context,
        val db: DietDB
) : DietRepository {

    private val recipes = mapOf(
            1L to Recipe("Американские блинчики с корицей", 10, 304, setOf("Сладкое", "Печёное")),
            2L to Recipe("Оввяная каша", 10, 304, setOf("Сладкое", "Печёное")),
            3L to Recipe("Яичница", 10, 304, setOf("Сладкое", "Печёное")),
            4L to Recipe("Картошка", 10, 304, setOf("Сладкое", "Печёное")),
            5L to Recipe("Ленивцы", 10, 304, setOf("Сладкое", "Печёное"))
    )

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
                    .toList()

            val settings = DietSettings(mealTypes, planDays)

            it.onNext(settings)
        }

        it.onComplete()
    }

    override fun writeDietSettings(dietSettings: DietSettings): Observable<Any> =
            Observable.create {

                preferences
                        .edit()
                        .putInt(DIET_SETTINGS_PLAN_DAYS, dietSettings.planDays)
                        .apply()

                dietSettings.mealTypes.forEach {
                    db.mealTypeDao().addMealType(it.dbEntity)
                }

                it.onComplete()
            }


    override fun getMealsForDate(date: DateTime): List<Meal> = getMealsForPeriod(date, date, null)

    override fun getMealsForPeriod(dateFrom: DateTime, dateTo: DateTime, mealType: MealType?): List<Meal> {
            return if (mealType == null)
                 emptyList()
            // db.mealDao().getMeals(dateFrom, dateTo)
            else
                emptyList()
//                db.mealDao().getMeals(dateFrom, dateTo, mealType.toDBEntity()))
//                    .map {
//                        Meal(
//                                MealType("", 1), recipes[it.recipeId] ?: DEFAULT_RECIPE, it.date
//                        )
//                    }
//                    .toList()
    }

    override fun writeMeal(meal: Meal) {

//        db.mealDao().addMeal(meal)
    }

    companion object {

        private const val DIET_SETTINGS_PLAN_DAYS = "plan_days"

        private const val DIET_PREFERENCES = "diet_prefs"

        private val DEFAULT_RECIPE = Recipe("default", 0, 0, emptySet())
    }
}