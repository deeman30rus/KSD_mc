package com.delizarov.ksmartdiet.presentation

import com.delizarov.ksmartdiet.navigation.NavigationController

interface BaseView

abstract class BasePresenter<T : BaseView> {

    protected lateinit var view: T
    protected lateinit var navController: NavigationController

    fun addNavController(navigationController: NavigationController) {
        navController = navigationController
    }

    fun attachView(view: T) {
        this.view = view
    }

    open fun onViewCreated() {

    }
}