package com.delizarov.ksmartdiet.presentation

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface BaseView

abstract class BasePresenter<T : BaseView> {

    protected lateinit var view: T

    fun attachView(view: T) {
        this.view = view
    }

    fun onViewCreated() {

    }
}