package com.delizarov.ksmartdiet.ui.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.delizarov.common.x.ui.bind
import com.delizarov.customviews.PlanDaysView
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.DietSettings

class SettingsView(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    var settings: DietSettings = DEFAULT_SETTINGS
        set(value) {

            field = value
            render()
        }

    var onSettingsChanged: (DietSettings) -> Unit = {}

    private val planDays: PlanDaysView by bind(R.id.plan_days_amount)
    private val mealTypes: EditMealTypesView by bind(R.id.meal_types)

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    init {

        inflate(context, R.layout.view_settings, this)

        render()
    }

    private fun render() {

        planDays.amount = settings.planDays
        planDays.onAmountChangedListener = { amount ->

            settings.planDays = amount

            onSettingsChanged(settings)
        }

        mealTypes.mealTypes = settings.mealTypes
        mealTypes.onMealTypesChanged = {

            settings.mealTypes.clear()
            settings.mealTypes.addAll(it)

            onSettingsChanged(settings)
        }
    }
}

private val DEFAULT_SETTINGS = DietSettings(mutableListOf(), 7)