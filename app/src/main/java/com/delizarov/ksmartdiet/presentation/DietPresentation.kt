package com.delizarov.ksmartdiet.presentation

import com.delizarov.ksmartdiet.domain.interactors.GetMealUseCase
import com.delizarov.ksmartdiet.domain.interactors.ReadDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.interactors.SaveDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import org.joda.time.DateTime
import javax.inject.Inject

interface DietView : BaseView {
    fun showDietSettingsDialog()
    fun dismissDietSettingsDialog()
    fun showPlanDaysMenu(days: List<DateTime>)
    fun showDailyMeals(meals: List<Meal>)
}

class DietPresenter @Inject constructor(
        private val readDietSettingsUseCase: ReadDietSettingsUseCase,
        private val saveDietSettingsUseCase: SaveDietSettingsUseCase,
        private val getMealUseCase: GetMealUseCase
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

        val curDate = DateTime()
        val days = ArrayList<DateTime>()

        days.add(curDate)

        for (i in 1 until settings.planDays - 1)
            days.add(curDate.plusDays(i))

        view.showPlanDaysMenu(days)
    }

    fun onSelectedDateChanged(it: DateTime) {

//        val params = DateParams(it)
//
//        getMealUseCase
//                .observable(params)
//                .observeOn(AndroidSchedulers.mainThread())
//                .toList()
//                .subscribe{meals ->
//
//                    view.showDailyMeals(meals)
//                }


    }
}