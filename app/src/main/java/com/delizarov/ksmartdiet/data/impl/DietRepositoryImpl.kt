package com.delizarov.ksmartdiet.data.impl

import android.content.Context
import android.content.SharedPreferences
import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.domain.DietSettingsNotFoundException
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.domain.models.MealType
import com.delizarov.ksmartdiet.domain.models.Recipe
import io.reactivex.Observable
import javax.inject.Inject
import com.google.gson.Gson
import io.reactivex.ObservableOnSubscribe
import org.joda.time.DateTime


class DietRepositoryImpl @Inject constructor(ctx: Context) : DietRepository {

    private val preferences: SharedPreferences

    init {
        preferences = ctx.getSharedPreferences(DIET_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun getDietSettings(): Observable<DietSettings> = Observable.create {


        val gson = Gson()
        val json = preferences.getString(DIET_PREFERENCES, "")

        if (json.isEmpty())
            it.onError(DietSettingsNotFoundException())
        else {
            val settings = gson.fromJson<DietSettings>(json, DietSettings::class.java)
            it.onNext(settings)
        }

        it.onComplete()
    }

    override fun saveDietSettings(dietSettings: DietSettings) : Observable<Any> =
            Observable.create {

                val gson = Gson()
                val json = gson.toJson(dietSettings)

                preferences
                        .edit()
                        .putString(DIET_PREFERENCES, json)
                        .apply()

                it.onComplete()

            }


    override fun readMealsForDate(date: DateTime): Observable<Meal> {

        val pancakes = Recipe(
                "Американские панкейки с корицей",
                15,
                300,
                setOf("сладкое", "печёное")
        )

        val meals = listOf(
                Meal(MealType("Завтрак", 1), pancakes),
                Meal(MealType("Обед", 2), pancakes),
                Meal(MealType("Ужин", 3), pancakes)
        )

        return Observable.fromIterable(meals)
    }

    companion object {
        private const val DIET_PREFERENCES = "diet_prefs"

    }
}