package com.delizarov.ksmartdiet.di

import android.app.Application
import android.content.Context
import com.delizarov.ksmartdiet.data.UserRepository
import com.delizarov.ksmartdiet.data.impl.UserRepositoryImpl
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
class DataRepositoryModule(private val app: Application) {

    @Provides
    fun providesUserRepository(repository: UserRepositoryImpl): UserRepository = repository
}
