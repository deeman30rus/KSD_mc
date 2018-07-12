package com.delizarov.ksmartdiet.data.db

import android.arch.persistence.room.TypeConverter
import com.delizarov.ksmartdiet.domain.models.MealType
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

fun MealTypeEntity.toMealType() = MealType(this.name, this.order)

fun MealType.toDBEntity() = MealTypeEntity(this.name, this.order)

class Converters {

    private val formatter = ISODateTimeFormat.dateTimeParser().withOffsetParsed()

    @TypeConverter
    fun toDateTime(value: String?): DateTime? {
        return value?.let {
            return formatter.parseDateTime(value)
        }
    }

    @TypeConverter
    fun fromDateTime(date: DateTime?): String? = date?.toString(formatter)
}