package com.delizarov.ksmartdiet

import android.app.Application
import com.delizarov.ksmartdiet.di.AppComponent
import com.delizarov.ksmartdiet.di.ApplicationModule
import com.delizarov.ksmartdiet.di.DaggerAppComponent
import com.delizarov.ksmartdiet.di.DataRepositoryModule
import com.facebook.stetho.Stetho

class SmartDietApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .dataRepositoryModule(DataRepositoryModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        component.inject(this)
    }
}