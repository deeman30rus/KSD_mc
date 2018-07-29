package com.delizarov.ksmartdiet.presentation

import com.delizarov.ksmartdiet.domain.DietSettingsNotFoundException
import com.delizarov.ksmartdiet.domain.interactors.DateParams
import com.delizarov.ksmartdiet.domain.interactors.GetMealUseCase
import com.delizarov.ksmartdiet.domain.interactors.ReadDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.interactors.SuggestMealUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import org.joda.time.DateTime
import javax.inject.Inject

interface DietView : BaseView {
    fun showPlanDaysMenu(days: List<DateTime>)
    fun showDailyMeals(meals: List<Meal>)
    fun displaySettingsScreen()
    fun close()
    fun displayFeatureNotImplementedYet()
    fun switchCurrentMealTo(oldMeal: Meal, newMeal: Meal)
}

class DietPresenter @Inject constructor(
        private val readDietSettingsUseCase: ReadDietSettingsUseCase,
        private val getMealUseCase: GetMealUseCase,
        private val suggestMealUseCase: SuggestMealUseCase
) : BasePresenter<DietView>() {

    private lateinit var settings: DietSettings

    override fun onViewCreated() {

        readDietSettingsUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    settings = it
                    renderDietScreen(settings)
                }, {

                    when (it) {
                        is DietSettingsNotFoundException -> view.displaySettingsScreen()
                        else -> view.close()
                    }
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

        val params = DateParams(it, settings)

        getMealUseCase
                .observable(params)
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe { meals ->
                    view.showDailyMeals(meals)
                }


    }

    fun onSuggestButtonClicked(currentMeal: Meal) {

        suggestMealUseCase
                .observable(currentMeal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.switchCurrentMealTo(currentMeal, it)
                }
    }

    fun onPickManuallyButtonClicked() {

        view.displayFeatureNotImplementedYet()
    }
}