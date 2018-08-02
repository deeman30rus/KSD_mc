package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.TypeConverter
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.domain.models.MealType
import com.delizarov.ksmartdiet.domain.models.Recipe
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

val MealTypeEntity.kModel: MealType
    get() = MealType(this.name, this.order)

val MealType.dbEntity: MealTypeEntity
    get() = MealTypeEntity(this.name, this.order)

val Meal.dbEntity: MealEntity
    get() = MealEntity(this.type.name, this.recipe.id ?: -1L, this.date)


fun MealWithVerboseTypeEntity.kModel(recipe: Recipe) = Meal(this.type.kModel, recipe, this.date)

class Converters {

    private val formatter = DateTimeFormat.forPattern("dd-MM-yyyy")

    @TypeConverter
    fun toDateTime(value: String?): DateTime? {
        return value?.let {
            return formatter.parseDateTime(value)
        }
    }

    @TypeConverter
    fun fromDateTime(date: DateTime?): String? = date?.toString(formatter)
}