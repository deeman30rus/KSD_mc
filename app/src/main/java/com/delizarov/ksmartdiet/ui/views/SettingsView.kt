package com.delizarov.ksmartdiet.ui.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.Button
import com.delizarov.common.x.ui.bind
import com.delizarov.customviews.EditMealTypesView
import com.delizarov.customviews.PlanDaysView
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.DietSettings

class SettingsView(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    private var settings: DietSettings = DEFAULT_SETTINGS
        set(value) {

        }

    var onSaveButtonClickedListener: (DietSettings) -> Unit = { }

    private val planDays: PlanDaysView by bind(R.id.plan_days_amount)
    private val saveButton: Button by bind(R.id.save)
    private val mealTypes: EditMealTypesView by bind(R.id.meal_types)

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    init {

        inflate(context, R.layout.view_settings, this)


    }

    private fun render() {

        planDays.amount = settings.planDays

        mealTypes // todo сделать, чтобы принимал и возвращал meal types

        updateSaveButtonAvailability(mealTypes.isDataSetCorrect)


        mealTypes.onDataSetChangedListener = { _, isCorrect ->

            updateSaveButtonAvailability(isCorrect)
        }

        saveButton.setOnClickListener {
            onSaveButtonClickedListener(settings)
        }
    }

    private fun updateSaveButtonAvailability(enabled: Boolean) {

        saveButton.isEnabled = enabled
    }
}

private val DEFAULT_SETTINGS = DietSettings(emptyList(), 7)