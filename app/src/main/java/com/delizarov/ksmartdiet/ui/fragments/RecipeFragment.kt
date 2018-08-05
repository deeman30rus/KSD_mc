package com.delizarov.ksmartdiet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.navigation.ScreenKeyHolder

class RecipeFragment : BaseFragment(), ScreenKeyHolder  {

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override val screenKey = ScreenKeys.RecipeScreenKey


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_recipe, container, false)

        return v
    }
}