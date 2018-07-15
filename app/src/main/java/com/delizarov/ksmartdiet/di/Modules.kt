package com.delizarov.ksmartdiet.di

import android.app.Application
import android.content.Context
import com.delizarov.ksmartdiet.SmartDietApplication
import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.data.UserRepository
import com.delizarov.ksmartdiet.data.db.DietDB
import com.delizarov.ksmartdiet.data.impl.DietRepositoryImpl
import com.delizarov.ksmartdiet.data.impl.UserRepositoryImpl
import com.delizarov.ksmartdiet.ui.activities.BaseActivity
import com.delizarov.ksmartdiet.ui.activities.MainActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    fun provideApplicationContext(): Context = app
}

@Module
class DataRepositoryModule(app: Application) {

    private val app: SmartDietApplication = app as SmartDietApplication

    @Provides
    fun providesUserRepository(repository: UserRepositoryImpl): UserRepository = repository

    @Provides
    fun providesDietRepository(repository: DietRepositoryImpl): DietRepository = repository

    @Provides
    fun provideDietDB():DietDB = app.db
}