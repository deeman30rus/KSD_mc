package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(
        version = 1,
        entities = [(MealEntity::class)]
        )
@TypeConverters(
    Converters::class
)
abstract class DietDB : RoomDatabase() {

    abstract fun mealDao(): MealDao

    abstract fun mealTypeDao(): MealTypeDao
}