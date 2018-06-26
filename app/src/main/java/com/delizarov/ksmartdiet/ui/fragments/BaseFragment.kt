package com.delizarov.ksmartdiet.ui.fragments

import android.support.v4.app.Fragment
import com.delizarov.ksmartdiet.navigation.NavController
import com.delizarov.ksmartdiet.SmartDietApplication

abstract class BaseFragment : Fragment() {

    protected fun getAppComponent() = (activity!!.application as SmartDietApplication).component

    protected lateinit var navController: NavController
}