package com.delizarov.ksmartdiet.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import com.delizarov.common.x.ui.bind
import com.delizarov.customviews.ExtendableList
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.MealType
import fr.ganfra.materialspinner.MaterialSpinner

class DietSettingsDialog (context: Context, private val onSaveSettingsListener: (DietSettings) -> Unit) : Dialog(context) {

    private val planDays: MaterialSpinner by bind(R.id.plan_days_amount)
    private val saveButton: Button by bind(R.id.save)
    private val mealTypes: ExtendableList by bind(R.id.meal_types)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_diet_settings)

        setCancelable(false)

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, context.resources.getStringArray(R.array.plan_days))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planDays.setSelection(4)
        planDays.adapter = adapter

        saveButton.setOnClickListener {

            val types = mealTypes.getEntries()

            if (types.isEmpty() || types.any {it.value.isEmpty()} ) {
                mealTypes.error = context.getString(R.string.empty_meal_types_error)
                return@setOnClickListener
            }
            var index = 0
            val settings = DietSettings(
                    types
                            .asSequence()
                            .map{
                                MealType(it.value, index++)
                            }.toList(),
                    (planDays.selectedItem as String).toInt()
            )

            onSaveSettingsListener(settings)
        }
    }
}