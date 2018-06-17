package com.delizarov.ksmartdiet.di

import android.app.Application
import com.delizarov.ksmartdiet.ui.activities.MainActivity
import com.delizarov.ksmartdiet.ui.activities.StartActivity
import com.delizarov.ksmartdiet.ui.fargments.DietFragment
import com.delizarov.ksmartdiet.ui.fargments.LoginFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = arrayOf(
                ApplicationModule::class,
                DataRepositoryModule::class
        )
)
interface AppComponent {

    fun inject(app: Application)

    fun inject(app: StartActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(loginFragment: LoginFragment)

    fun inject(dietFragment: DietFragment)
}