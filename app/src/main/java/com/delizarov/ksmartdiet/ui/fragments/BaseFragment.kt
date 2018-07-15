package com.delizarov.ksmartdiet.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.delizarov.ksmartdiet.SmartDietApplication
import com.delizarov.ksmartdiet.di.AppComponent
import com.delizarov.ksmartdiet.navigation.NavigationController
import com.delizarov.ksmartdiet.ui.activities.MainActivity

abstract class BaseFragment : Fragment() {

    protected val appComponent: AppComponent
            get() = (activity!!.application as SmartDietApplication).component

    // todo костыль поменять на DI
    protected val navController: NavigationController by lazy {
        (activity as MainActivity).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectComponents()
    }

    protected abstract fun injectComponents()
}