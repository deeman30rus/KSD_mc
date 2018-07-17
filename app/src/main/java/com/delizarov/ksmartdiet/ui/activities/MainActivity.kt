package com.delizarov.ksmartdiet.ui.activities

import android.os.Bundle
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.navigation.NavigationController
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.navigation.impl.FragmentFactoryImpl

class MainActivity : BaseActivity() {

    val navController = NavigationController(
            FragmentFactoryImpl(),
            R.id.container,
            supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        val screen = intent.getStringExtra(SCREEN)

        when (screen) {
            ScreenKeys.SignInScreenKey -> navToLoginView()
            ScreenKeys.SettingsScreenKey -> navToSettingsView()
            ScreenKeys.DailyDietScreenKey -> navToDietView()
            else -> finish()
        }
    }

    override fun injectComponents() {
        appComponent.inject(this)
    }

    private fun navToLoginView() = navController.fwdToSignInScreen()

    private fun navToSettingsView() = navController.fwdToSettingsScreen(true)

    private fun navToDietView() = navController.fwdToDailyDietScreen()


    companion object {

        const val SCREEN = "Screen"
    }
}