package com.delizarov.ksmartdiet.ui.fargments

import android.support.v4.app.Fragment
import com.delizarov.ksmartdiet.NavController
import com.delizarov.ksmartdiet.SmartDietApplication

abstract class BaseFragment : Fragment() {

    protected fun getAppComponent() = (activity!!.application as SmartDietApplication).component

    protected lateinit var navController: NavController
}