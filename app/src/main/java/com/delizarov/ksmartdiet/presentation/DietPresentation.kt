package com.delizarov.ksmartdiet.presentation

import com.delizarov.ksmartdiet.domain.interactors.ReadDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.interactors.SaveDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import io.reactivex.android.schedulers.AndroidSchedulers
import org.joda.time.DateTime
import javax.inject.Inject

interface DietView : BaseView {
    fun showDietSettingsDialog()
    fun dismissDietSettingsDialog()
    fun showPlanDaysMenu(planDays: Int)
}

class DietPresenter @Inject constructor(
        private val readDietSettingsUseCase: ReadDietSettingsUseCase,
        private val saveDietSettingsUseCase: SaveDietSettingsUseCase
) : BasePresenter<DietView>() {

    override fun onViewCreated() {

        readDietSettingsUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    renderDietScreen(it)
                }, {

                    view.showDietSettingsDialog()
                })
    }

    fun onSettingsSaveClicked(settings: DietSettings) {

        saveDietSettingsUseCase
                .observable(settings)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {}, {

                    view.dismissDietSettingsDialog()
                    renderDietScreen(settings)
                })
        
    }

    private fun renderDietScreen(settings: DietSettings) {

        view.showPlanDaysMenu(settings.planDays)
    }

    fun onSelectedDateChanged(it: DateTime?) {

    }
}