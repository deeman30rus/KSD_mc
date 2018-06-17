package com.delizarov.ksmartdiet.ui.activities

import android.support.v7.app.AppCompatActivity
import com.delizarov.ksmartdiet.SmartDietApplication

open class BaseActivity : AppCompatActivity() {

    protected fun getAppComponent() = (applicationContext as SmartDietApplication).component
}