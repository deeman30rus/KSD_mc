package com.delizarov.ksmartdiet.presentation

import com.delizarov.common.presentation.BasePresenter
import com.delizarov.common.presentation.BaseView
import com.delizarov.ksmartdiet.domain.DietSettingsNotFoundException
import com.delizarov.ksmartdiet.domain.interactors.*
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.domain.models.Recipe
import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import org.joda.time.DateTime
import javax.inject.Inject

interface DietView : BaseView {
    fun showPlanDaysMenu(daysAmount: Int)
    fun showDailyMeals(meals: List<Meal>)
    fun displaySettingsScreen()
    fun close()
    fun displayFeatureNotImplementedYet()
    fun switchCurrentMealTo(oldMeal: Meal, newMeal: Meal)
    fun renderUserInfo(userInfo: UserInfo)
    fun showRecipeScreen(recipe: Recipe)
    fun displayProfileScreen(userInfo: UserInfo)
}

class DietPresenter @Inject constructor(
        private val readDietSettingsUseCase: ReadDietSettingsUseCase,
        private val getMealUseCase: GetMealUseCase,
        private val readUserInfoUseCase: ReadUserInfoUseCase,
        private val suggestMealUseCase: SuggestMealUseCase
) : BasePresenter<DietView>() {

    private lateinit var settings: DietSettings

    override fun <R> onViewCreated(data: R?) {

        readDietSettingsUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    settings = it
                    renderDietScreen(settings)
                    updateDayMeals(DateTime.now())

                }, {

                    when (it) {
                        is DietSettingsNotFoundException -> view.displaySettingsScreen()
                        else -> view.close()
                    }
                })

        readUserInfoUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.renderUserInfo(it)
                }
    }

    private fun renderDietScreen(settings: DietSettings) {

        view.showPlanDaysMenu(settings.planDays)
    }

    fun onSelectedDateChanged(day: DateTime) = updateDayMeals(day)

    fun onSuggestButtonClicked(currentMeal: Meal) {

        suggestMealUseCase
                .observable(currentMeal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.switchCurrentMealTo(currentMeal, it)
                }
    }

    fun onPickManuallyButtonClicked() = view.displayFeatureNotImplementedYet()


    fun onMealClicked(meal: Meal) = view.showRecipeScreen(meal.recipe)

    fun onProfileClicked(userInfo: UserInfo) = view.displayProfileScreen(userInfo)

    private fun updateDayMeals(day: DateTime) {

        val params = DateParams(day, settings)

        getMealUseCase
                .observable(params)
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe { meals ->
                    view.showDailyMeals(meals)
                }

    }
}