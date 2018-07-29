package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.*
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.domain.models.MealType
import org.joda.time.DateTime

@Dao
interface MealDao {

    @Query("""
        select m.recipe_id, m.date, mt.name, mt.`order`
        from meals m
        inner join meal_types mt on m.type = mt.name
        where
            date between :dateFrom and :dateTo
        order by date""")
    fun getMeals(dateFrom: DateTime, dateTo: DateTime): List<MealWithVerboseTypeEntity>

    @Query("""
        select m.recipe_id, m.date, mt.name, mt.`order`
        from meals m
        inner join meal_types mt on m.type = mt.name
        where
            m.type = :mealType and
            m.date between :dateFrom and :dateTo
        order by date""")
    fun getMeals(dateFrom: DateTime, dateTo: DateTime, mealType: String): List<MealWithVerboseTypeEntity>


    @Insert
    fun addMeal(meal: MealEntity)

    @Update
    fun updateMeal(dbEntity: MealEntity)
}

@Dao
interface MealTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMealType(mealType: MealTypeEntity)

    @Query("select * from meal_types")
    fun getMealTypes(): List<MealTypeEntity>
}