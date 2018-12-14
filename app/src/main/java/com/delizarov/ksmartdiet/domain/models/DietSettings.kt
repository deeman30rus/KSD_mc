package com.delizarov.ksmartdiet.domain.models

import java.io.Serializable

class DietSettings(
        val mealTypes: MutableList<MealType>,
        var planDays: Int
) : Serializable