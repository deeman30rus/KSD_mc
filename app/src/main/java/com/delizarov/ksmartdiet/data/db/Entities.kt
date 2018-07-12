package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime


@Entity(tableName = "meals")
class MealEntity(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "type") val type: String,
        @ColumnInfo(name = "recipe_id") val recipeId: Long,
        @ColumnInfo(name = "date") val date: DateTime

)

@Entity(tableName = "meal_types")
class MealTypeEntity(
        @PrimaryKey val name: String,
        @ColumnInfo(name = "order") val order: Int
)