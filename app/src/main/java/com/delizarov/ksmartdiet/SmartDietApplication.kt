package com.delizarov.ksmartdiet

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.delizarov.ksmartdiet.data.db.DietDB
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

    val db: DietDB by lazy {
        Room.databaseBuilder(this, DietDB::class.java, "app.db").build()
    }


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        initDB()

        component.inject(this)
    }

    private fun initDB() {

        // из-за отложенной инициализации вызываем простой метод, чтобы свойство проинициализировалось

        Log.v("smart diet application", if (db.isOpen) "db is open" else "sb is closed")
    }
}