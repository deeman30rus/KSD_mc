package com.delizarov.ksmartdiet.ui.activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.delizarov.ksmartdiet.navigation.NavController
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.fragments.DietFragment
import com.delizarov.ksmartdiet.ui.fragments.LoginFragment

class MainActivity : BaseActivity(), NavController {
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        getAppComponent().inject(this)

        fm = supportFragmentManager

        val screen = intent.getIntExtra(SCREEN, NO_SCREEN)

        when (screen) {
            LOGIN_SCREEN -> navToLoginView()
            DIET_SCREEN -> navToDietView()

            NO_SCREEN -> finish()
        }
    }

    override fun navToDietView() {

        val fragment = DietFragment.build {

            navController = this@MainActivity
        }

        fm
                .beginTransaction()
                .replace(R.id.container, fragment, ScreenKeys.DietScreenKey)
                .commit()
    }

    override fun navToLoginView() {
        val fragment = LoginFragment.build {

            navController = this@MainActivity
        }

        fm
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    companion object {

        const val SCREEN = "Screen"

        const val NO_SCREEN = -1
        const val LOGIN_SCREEN = 1
        const val DIET_SCREEN = 2
    }
}