package com.delizarov.ksmartdiet.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.delizarov.ksmartdiet.SmartDietApplication
import com.delizarov.ksmartdiet.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    protected val appComponent: AppComponent
        get() = (applicationContext as SmartDietApplication).component

    protected abstract fun injectComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        injectComponents()
    }


}