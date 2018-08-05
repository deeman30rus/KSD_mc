package com.delizarov.ksmartdiet.presentation

import com.delizarov.common.presentation.BasePresenter
import com.delizarov.common.presentation.BaseView
import com.delizarov.ksmartdiet.domain.interactors.GetRecipeUseCase
import com.delizarov.ksmartdiet.domain.models.Recipe
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

interface RecipeView : BaseView {
    fun renderRecipe(recipe: Recipe)

}

class RecipePresenter @Inject constructor(
        private val getRecipeUseCase: GetRecipeUseCase
) : BasePresenter<RecipeView>() {

    override fun <R> onViewCreated(data: R?) {

        val recipeId = data as Long

        getRecipeUseCase
                .observable(recipeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.renderRecipe(it)
                }
    }
}