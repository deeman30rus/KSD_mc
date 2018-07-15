package com.delizarov.ksmartdiet.navigation

import android.support.v4.app.FragmentManager
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.UserInfo
import com.delizarov.navigation.FragmentFactory

class NavigationController(
        private val fragmentFactory: FragmentFactory,
        private val containerId: Int,
        private val fm: FragmentManager
) {

    fun fwdToSignInScreen() {

        val sKey = ScreenKeys.SignInScreenKey
        val fragment = fragmentFactory.create(sKey)

        fm
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(sKey)
                .commit()
    }

    fun fwdToDailyDietScreen() {

        val sKey = ScreenKeys.DailyDietScreenKey
        val fragment = fragmentFactory.create(sKey)

        fm
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(sKey)
                .commit()
    }

    fun fwdToSettingsScreen() {

        // userInfo - необязательный параметр, он нужен только при первом входе, чтобы передать его в основной экран

        val sKey = ScreenKeys.SettingsScreenKey
        val fragment = fragmentFactory.create(sKey)

        fm
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(sKey)
                .commit()
    }

    fun back() {

        fm.popBackStack()
    }
}