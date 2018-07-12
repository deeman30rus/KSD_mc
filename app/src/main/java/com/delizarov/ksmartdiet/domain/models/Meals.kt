package com.delizarov.ksmartdiet.domain.models

import org.joda.time.DateTime

class MealType(
        val name: String,
        val order: Int
)

data class Meal(
        val type: MealType,
        val recipe: Recipe,
        val date: DateTime
)