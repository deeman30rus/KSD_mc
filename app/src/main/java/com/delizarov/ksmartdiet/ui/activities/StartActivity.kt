package com.delizarov.ksmartdiet.ui.activities

import android.content.Intent
import android.os.Bundle
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.interactors.ReadUserInfoUseCase
import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import javax.inject.Inject

class StartActivity : BaseActivity() {

    @Inject
    lateinit var readUserInfoUseCase: ReadUserInfoUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        getAppComponent().inject(this)

        readUserInfoUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            showMainScreen(it)
                        },
                        {
                            showLoginScreen()
                        }
                )

        finish()
    }

    private fun showMainScreen(userInfo: UserInfo) {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(MainActivity.SCREEN, MainActivity.DIET_SCREEN)
        startActivity(intent)

        finish()
    }

    private fun showLoginScreen() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(MainActivity.SCREEN, MainActivity.LOGIN_SCREEN)
        startActivity(intent)

        finish()
    }
}
