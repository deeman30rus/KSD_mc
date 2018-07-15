package com.delizarov.ksmartdiet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import com.delizarov.customviews.ExtendableList
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.interactors.SaveDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.MealType
import fr.ganfra.materialspinner.MaterialSpinner
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var saveDietSettingsUseCase: SaveDietSettingsUseCase

    private lateinit var planDays: MaterialSpinner // by bind(R.id.plan_days_amount)
    private lateinit var saveButton: Button // by bind(R.id.save)
    private lateinit var mealTypes: ExtendableList // by bind(R.id.meal_types)

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_settings, container, false)

        planDays = v.findViewById(R.id.plan_days_amount)
        saveButton = v.findViewById(R.id.save)
        mealTypes = v.findViewById(R.id.meal_types)

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, context!!.resources.getStringArray(R.array.plan_days))

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planDays.setSelection(4)
        planDays.adapter = adapter

        saveButton.setOnClickListener {

            val types = mealTypes.getEntries()

            if (types.isEmpty() || types.any { it.value.isEmpty() }) {
                mealTypes.error = context!!.getString(R.string.empty_meal_types_error)
                return@setOnClickListener
            }
            var index = 0
            val settings = DietSettings(
                    types
                            .asSequence()
                            .map {
                                MealType(it.value, index++)
                            }.toList(),
                    (planDays.selectedItem as String).toInt()
            )

            saveDietSettingsUseCase
                    .observable(settings)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {}, {

                        navController.fwdToDailyDietScreen()
                        //renderDietScreen(settings)
                    })

        }

        return v
    }
}