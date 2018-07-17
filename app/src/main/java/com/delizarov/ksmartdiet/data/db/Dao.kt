package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.domain.models.MealType
import org.joda.time.DateTime

@Dao
interface MealDao {

//    @Query("select * from meals where date between :dateFrom and :dateTo order by date")
//    fun getMeals(dateFrom: DateTime, dateTo: DateTime): List<MealEntity>
//
//    @Query("select * from meals where date between :dateFrom and :dateTo and type order by date")
//    fun getMeals(dateFrom: DateTime, dateTo: DateTime, type: MealTypeEntity): List<MealEntity>
//
//    @Insert
//    fun addMeal(meal: Meal)
}

@Dao
interface MealTypeDao {

    @Insert
    fun addMealType(mealType: MealTypeEntity)

    @Query("select * from meal_types")
    fun getMealTypes(): List<MealTypeEntity>
}