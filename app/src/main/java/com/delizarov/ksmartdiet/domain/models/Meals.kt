package com.delizarov.ksmartdiet.domain.models

class MealType(
        val name: String,
        val order: Int
)

data class Meal(
        val type: MealType,
        val recipe: Recipe
)