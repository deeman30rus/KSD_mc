package com.delizarov.ksmartdiet.presentation

import com.delizarov.common.presentation.BasePresenter
import com.delizarov.common.presentation.BaseView
import com.delizarov.ksmartdiet.domain.interactors.LogOutUseCase
import com.delizarov.ksmartdiet.domain.interactors.ReadDietSettingsUseCase
import com.delizarov.ksmartdiet.domain.models.DietSettings
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

interface ProfileView : BaseView {

    fun displayLogOutDialog()
    fun displaySplashScreen()
    fun displaySettings(settings: DietSettings)
}

class ProfilePresenter @Inject constructor(
        private val logOutUseCase: LogOutUseCase,
        private val readSettingsUseCase: ReadDietSettingsUseCase
) : BasePresenter<ProfileView>() {

    override fun <R> onViewCreated(data: R?) {

        readSettingsUseCase
                .observable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.displaySettings(it)
                }
    }

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