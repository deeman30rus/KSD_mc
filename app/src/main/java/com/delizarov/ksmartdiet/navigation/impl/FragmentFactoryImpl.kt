package com.delizarov.ksmartdiet.navigation.impl

import android.support.v4.app.Fragment
import com.delizarov.ksmartdiet.domain.models.UserInfo
import com.delizarov.ksmartdiet.navigation.NoSuchScreenException
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.fragments.DietFragment
import com.delizarov.ksmartdiet.ui.fragments.LoginFragment
import com.delizarov.ksmartdiet.ui.fragments.SettingsFragment
import com.delizarov.navigation.FragmentFactory

class FragmentFactoryImpl : FragmentFactory() {

    override fun <T, R> create(screenKey: String, data1: T?, data2: R?): Fragment =
            when (screenKey) {
                ScreenKeys.SignInScreenKey -> createLoginFragment(data1, data2)
                ScreenKeys.DailyDietScreenKey -> createDailyDietFragment(data1, data2)
                ScreenKeys.SettingsScreenKey -> createSettingsFragment(data1, data2)
                else -> throw NoSuchScreenException(screenKey)
            }

    private fun <T, R> createLoginFragment(data1: T?, data2: R?) = LoginFragment()

    private fun <T, R> createSettingsFragment(data1: T?, data2: R?) = SettingsFragment()

    private fun <T, R> createDailyDietFragment(data1: T?, data2: R?) = DietFragment()
}