package com.delizarov.ksmartdiet.domain.models

import java.io.Serializable

class DietSettings(
        val mealTypes: List<MealType>,
        val planDays: Int
) : Serializable