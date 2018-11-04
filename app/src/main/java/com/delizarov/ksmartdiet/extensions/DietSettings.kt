package com.delizarov.ksmartdiet.extensions

import com.delizarov.ksmartdiet.domain.models.DietSettings

val DietSettings.isCorrect: Boolean
    get() {

        if (this.mealTypes.isEmpty())
            return false

        for (mealType in this.mealTypes)
            if (mealType.name.isBlank())
                return false

        return true
    }
