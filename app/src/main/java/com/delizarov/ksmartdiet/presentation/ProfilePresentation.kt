package com.delizarov.ksmartdiet.presentation

import com.delizarov.common.presentation.BasePresenter
import com.delizarov.common.presentation.BaseView
import com.delizarov.ksmartdiet.domain.interactors.LogOutUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

interface ProfileView : BaseView {

    fun displayLogOutDialog()
    fun displaySplashScreen()
}

class ProfilePresenter @Inject constructor(
        private val logOutUseCase: LogOutUseCase
) : BasePresenter<ProfileView>() {

    fun onUserNameClicked() = view.displayLogOutDialog()
    fun onUserEmailClicked() = view.displayLogOutDialog()
    fun onIconDownClicked() = view.displayLogOutDialog()

    fun onLogOutButtonClicked() {

        logOutUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { view.displaySplashScreen() }
    }

}