package com.delizarov.ksmartdiet.domain.models

import org.joda.time.DateTime
import java.io.Serializable

class MealType(
        val name: String,
        val order: Int
) : Serializable

data class Meal(
        val type: MealType,
        val recipe: Recipe,
        val date: DateTime
)