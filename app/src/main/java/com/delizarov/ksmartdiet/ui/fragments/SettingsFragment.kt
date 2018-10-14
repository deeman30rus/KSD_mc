package com.delizarov.ksmartdiet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.delizarov.customviews.PlanDaysView
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.interactors.SaveDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.MealType
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.views.EditMealTypesView
import com.delizarov.navigation.ScreenKeyHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SettingsFragment : BaseFragment(), ScreenKeyHolder {

    override val screenKey = ScreenKeys.SettingsScreenKey

    @Inject
    lateinit var saveDietSettingsUseCase: SaveDietSettingsUseCase

    private lateinit var planDays: PlanDaysView
    private lateinit var saveButton: Button
    private lateinit var mealTypes: EditMealTypesView

    private var navToDietScreenAfterSave: Boolean = false

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_settings, container, false)

        planDays = v.findViewById(R.id.plan_days_amount)
        saveButton = v.findViewById(R.id.save)
        mealTypes = v.findViewById(R.id.meal_types)

        updateSaveButtonAvailability(mealTypes.isDataSetCorrect)

        mealTypes.onDataSetChangedListener = { _, isCorrect ->

            updateSaveButtonAvailability(isCorrect)
        }

        saveButton.setOnClickListener {

            val settings = DietSettings(
                    mealTypes.values,
                    planDays.amount
            )

            saveDietSettingsUseCase
                    .observable(settings)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {}, {

                        if (navToDietScreenAfterSave)
                            navController.setRoot(ScreenKeys.DailyDietScreenKey)
                    })
        }

        return v
    }

    private fun updateSaveButtonAvailability(enabled: Boolean) {

        saveButton.isEnabled = enabled
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {

        var navToDietScreenAfterSave: Boolean = false

        fun build(): SettingsFragment {

            val fragment = SettingsFragment()

            fragment.navToDietScreenAfterSave = this.navToDietScreenAfterSave

            return fragment
        }
    }
}