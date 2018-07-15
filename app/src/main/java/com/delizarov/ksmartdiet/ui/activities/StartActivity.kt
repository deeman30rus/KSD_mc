package com.delizarov.ksmartdiet.ui.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.DietSettingsNotFoundException
import com.delizarov.ksmartdiet.domain.interactors.ReadDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.interactors.ReadUserInfoUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.UserInfo
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class StartActivity : BaseActivity() {

    @Inject
    lateinit var readUserInfoUseCase: ReadUserInfoUseCase

    @Inject
    lateinit var readDietSettingsUseCase: ReadDietSettingsUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        readUserInfoUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _ ->
                            readDietSettingsUseCase
                                    .observable()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({ _ ->
                                        showDailyDietScreen()
                                    }) {
                                        when (it) {
                                            is DietSettingsNotFoundException -> showSettingsScreen()
                                            else -> TODO("show error")
                                        }
                                    }
                        },
                        {
                            showLoginScreen()
                        }
                )
    }

    override fun injectComponents() {

        appComponent.inject(this)
    }

    private fun showSettingsScreen() {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(MainActivity.SCREEN, ScreenKeys.SettingsScreenKey)

        startActivity(intent)

        finish()
    }

    private fun showDailyDietScreen() {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(MainActivity.SCREEN, ScreenKeys.DailyDietScreenKey)

        startActivity(intent)

        finish()
    }

    private fun showLoginScreen() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(MainActivity.SCREEN, ScreenKeys.SignInScreenKey)
        startActivity(intent)

        finish()
    }
}
