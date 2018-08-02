package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import com.delizarov.ksmartdiet.data.impl.DietRepositoryImpl
import com.delizarov.ksmartdiet.data.impl.gRecipes
import com.delizarov.ksmartdiet.domain.models.Meal
import org.joda.time.DateTime


val MealWithVerboseTypeEntity.kModel: Meal
    get() = Meal(type.kModel, gRecipes[recipeId] ?: DietRepositoryImpl.DEFAULT_RECIPE ,date)

class MealWithVerboseTypeEntity(
        @ColumnInfo(name = "recipe_id") val recipeId: Long,
        @ColumnInfo(name = "date") val date: DateTime,
        @Embedded val type: MealTypeEntity
)