package com.delizarov.ksmartdiet.ui.activities

import android.os.Bundle
import android.view.WindowManager
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.di.MainActivityComponent
import com.delizarov.ksmartdiet.di.NavigationModule
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.navigation.impl.FragmentScreenFactory
import com.delizarov.navigation.NavigationController
import com.delizarov.navigation.android.FragmentRouter

class MainActivity : BaseActivity() {

    val navController = object : NavigationController(FragmentScreenFactory(), FragmentRouter(supportFragmentManager, R.id.container)) {
        override fun closeNavigationTree() = finish()
    }

    private val mainActivityComponent: MainActivityComponent by lazy {

        appComponent.addMainActivityComponent(
                NavigationModule(navController)
        )
    }


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
        mainActivityComponent.inject(this)
    }

    private fun navToLoginView() = navController.setRoot(ScreenKeys.SignInScreenKey)

    private fun navToSettingsView() = navController.setRoot(ScreenKeys.SettingsScreenKey, true)

    private fun navToDietView() = navController.setRoot(ScreenKeys.DailyDietScreenKey)

    override fun onBackPressed() {

        navController.back()
    }

    companion object {

        const val SCREEN = "Screen"
    }
}