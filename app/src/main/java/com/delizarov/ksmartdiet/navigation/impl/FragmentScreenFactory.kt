package com.delizarov.ksmartdiet.navigation.impl

import com.delizarov.ksmartdiet.navigation.NoSuchScreenException
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.fragments.DietFragment
import com.delizarov.ksmartdiet.ui.fragments.LoginFragment
import com.delizarov.ksmartdiet.ui.fragments.RecipeFragment
import com.delizarov.ksmartdiet.ui.fragments.SettingsFragment
import com.delizarov.navigation.ScreenFactory
import com.delizarov.navigation.ScreenKey
import com.delizarov.navigation.ScreenKeyHolder

class FragmentScreenFactory : ScreenFactory() {

    override fun <T, R, S> createScreen(screenKey: ScreenKey, data1: T?, data2: R?, data3: S?): ScreenKeyHolder =
            when (screenKey) {
                ScreenKeys.SignInScreenKey -> createLoginFragment(data1, data2, data3)
                ScreenKeys.SettingsScreenKey -> createSettingsFragment(data1, data2, data3)
                ScreenKeys.DailyDietScreenKey -> createDailyDietFragment(data1, data2, data3)
                ScreenKeys.RecipeScreenKey -> createRecipeFragment(data1, data2, data3)
                else -> throw NoSuchScreenException(screenKey)
            }

    private fun <T, R, S> createLoginFragment(data1: T?, data2: R?, data3: S?) = LoginFragment()

    private fun <T, R, S> createSettingsFragment(data1: T?, data2: R?, data3: S?) =
            when (data1) {
                is Boolean -> SettingsFragment.build {
                    navToDietScreenAfterSave = data1
                }
                else -> SettingsFragment()
            }

    private fun <T, R, S> createDailyDietFragment(data1: T?, data2: R?, data3: S?) = DietFragment()

    private fun <T, R, S> createRecipeFragment(data1: T?, data2: R?, data3: S?) =
            when(data1) {
                is Long -> RecipeFragment.build {
                    recipeId = data1
                }
                else -> RecipeFragment()
            }

}