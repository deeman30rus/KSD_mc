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
import com.delizarov.ksmartdiet.extensions.isCorrect
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.views.EditMealTypesView
import com.delizarov.ksmartdiet.ui.views.SettingsView
import com.delizarov.navigation.ScreenKeyHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SettingsFragment : BaseFragment(), ScreenKeyHolder {

    override val screenKey = ScreenKeys.SettingsScreenKey

    @Inject
    lateinit var saveDietSettingsUseCase: SaveDietSettingsUseCase

    lateinit var settingsView: SettingsView
    lateinit var saveButton: Button

    private var navToDietScreenAfterSave: Boolean = false

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_settings, container, false)

        settingsView = v.findViewById(R.id.settings_view)
        saveButton = v.findViewById(R.id.save)

        if (settingsView.settings.isCorrect)
            enableSaveButton()
        else
            disableSaveButton()

        settingsView.onSettingsChangedListener = { settings ->

            if (settings.isCorrect)
                enableSaveButton()
            else
                disableSaveButton()
        }

        saveButton.setOnClickListener {

            saveDietSettingsUseCase
                    .observable(settingsView.settings)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {}, {

                        if (navToDietScreenAfterSave)
                            navController.setRoot(ScreenKeys.DailyDietScreenKey)
                    })
        }

        return v
    }

    private fun enableSaveButton() {

        saveButton.isEnabled = true
    }

    private fun disableSaveButton() {
        saveButton.isEnabled = false

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