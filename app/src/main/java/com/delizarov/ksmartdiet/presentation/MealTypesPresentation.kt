package com.delizarov.ksmartdiet.presentation

import com.delizarov.common.presentation.BasePresenter
import com.delizarov.common.presentation.BaseView
import com.delizarov.ksmartdiet.domain.models.MealType

interface MealTypesView : BaseView {

    fun addMealType(mealType: MealType)

    fun addAllMealTypes(mealTypes: List<MealType>)

    fun removeMealType(mealType: MealType)

    fun clearMealTypes()
}

class MealTypesPresenter : BasePresenter<MealTypesView>() {

    var onModelChanged: (List<MealType>) -> Unit = {}

    var model: MutableList<MealType> = mutableListOf()
        set(value) {

            field = value

            view.clearMealTypes()
            view.addAllMealTypes(field)
        }

    fun onRemoveMealTypeClicked(mealType: MealType) {

        model.remove(mealType)
        view.removeMealType(mealType)

        onModelChanged(model)
    }

    fun onMealTypeNameChanged(mealType: MealType, newName: String) {

        val pos = model.indexOf(mealType)

        model[pos].name = newName

        onModelChanged(model)
    }

    fun onAddNewMealTypeClick() {

        val mealType = MealType("", model.nextOrderValue)

        model.add(mealType)
        view.addMealType(mealType)

        onModelChanged(model)
    }
}

private inline val MutableList<MealType>.nextOrderValue: Int
    get() = (this.maxBy { it.order }?.order ?: 0) + 1