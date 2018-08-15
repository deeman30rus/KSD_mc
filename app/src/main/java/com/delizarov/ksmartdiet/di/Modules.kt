package com.delizarov.ksmartdiet.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.annotation.IdRes
import com.delizarov.ksmartdiet.SmartDietApplication
import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.data.UserRepository
import com.delizarov.ksmartdiet.data.db.DietDB
import com.delizarov.ksmartdiet.data.impl.DietRepositoryImpl
import com.delizarov.ksmartdiet.data.impl.UserRepositoryImpl
import com.delizarov.ksmartdiet.domain.models.DiversityStrategy
import com.delizarov.ksmartdiet.domain.models.MealPickStrategy
import com.delizarov.ksmartdiet.navigation.impl.FragmentScreenFactory
import com.delizarov.ksmartdiet.ui.activities.BaseActivity
import com.delizarov.navigation.NavigationController
import com.delizarov.navigation.Router
import com.delizarov.navigation.ScreenFactory
import com.delizarov.navigation.android.FragmentRouter
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

@Module
class DietModule {

    @Provides
    fun providesMealPickStrategy(strategy: DiversityStrategy): MealPickStrategy = strategy
}

@Module
class NavigationModule(private val navController: NavigationController) {

    @Provides
    fun navigationController() = navController
}