package com.delizarov.common.presentation

interface BaseView

abstract class BasePresenter<T : BaseView> {

    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view
    }

    fun onViewCreated() = onViewCreated(null)

    open fun <R> onViewCreated(data: R?) {

    }
}