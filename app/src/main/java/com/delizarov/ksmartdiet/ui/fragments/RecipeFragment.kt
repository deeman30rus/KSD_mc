package com.delizarov.ksmartdiet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Recipe
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.views.RecipeDetailsDirections
import com.delizarov.ksmartdiet.ui.views.RecipeDetailsInfoView
import com.delizarov.ksmartdiet.ui.views.RecipeDetailsIngredients
import com.delizarov.navigation.ScreenKeyHolder

class RecipeFragment : BaseFragment(), ScreenKeyHolder {

    private lateinit var recipe: Recipe

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override val screenKey = ScreenKeys.RecipeScreenKey

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_recipe, container, false)

        v.findViewById<RecipeDetailsInfoView>(R.id.info).recipe = recipe
        v.findViewById<RecipeDetailsIngredients>(R.id.recipe_ingredients).recipe = recipe
        v.findViewById<RecipeDetailsDirections>(R.id.recipe_directions).recipe = recipe

        return v
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {

        lateinit var recipe: Recipe

        fun build(): RecipeFragment {

            val fragment = RecipeFragment()

            fragment.recipe = this.recipe

            return fragment
        }
    }
}