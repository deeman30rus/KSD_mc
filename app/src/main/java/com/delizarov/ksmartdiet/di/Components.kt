package com.delizarov.ksmartdiet.di

import android.app.Application
import com.delizarov.ksmartdiet.ui.activities.MainActivity
import com.delizarov.ksmartdiet.ui.activities.StartActivity
import com.delizarov.ksmartdiet.ui.fragments.*
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            (ApplicationModule::class),
            (DataRepositoryModule::class),
            (DietModule::class)
        ]
)
interface AppComponent {

    fun inject(app: Application)

    fun inject(app: StartActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(loginFragment: LoginFragment)

    fun inject(dietFragment: DietFragment)

    fun inject(settingsFragment: SettingsFragment)

    fun addMainActivityComponent(navModule: NavigationModule): MainActivityComponent

    fun inject(recipeFragment: RecipeFragment)

    fun inject(profileFragment: ProfileFragment)
}

@Subcomponent(
        modules = [
            (NavigationModule::class)
        ]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}