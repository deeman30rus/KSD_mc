package com.delizarov.ksmartdiet.presentation

interface BaseView

abstract class BasePresenter<T : BaseView> {

    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view
    }

    open fun onViewCreated() {

    }
}