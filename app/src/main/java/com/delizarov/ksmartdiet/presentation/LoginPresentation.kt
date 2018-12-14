package com.delizarov.ksmartdiet.presentation

import android.net.Uri
import com.delizarov.common.presentation.BasePresenter
import com.delizarov.common.presentation.BaseView
import com.delizarov.ksmartdiet.domain.interactors.SaveIdTokenUseCase
import com.delizarov.ksmartdiet.domain.interactors.SaveUserInfoUseCase
import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

interface LoginView : BaseView {
    fun displaySignInWithGoogle()
    fun showSignInError()
    fun displayDietScreen()
    fun showClientDataError()
}

class LoginPresenter @Inject constructor(
        private val saveUserInfoUseCase: SaveUserInfoUseCase,
        private val saveIdTokenUseCase: SaveIdTokenUseCase
) : BasePresenter<LoginView>() {


    fun onSignInWithGoogleClicked() {

        view.displaySignInWithGoogle()
    }

    fun onSignInSuccess(displayName: String?, idToken: String?, photoUrl: Uri?) {

        if (idToken == null) {
            view.showClientDataError()
            return
        }

        saveIdTokenUseCase
                .observable(idToken)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    saveUserInfoUseCase
                            .observable(UserInfo(displayName ?: "", photoUrl?.toString()))
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                view.displayDietScreen()
                            }
                            .subscribe()
                }
                .subscribe()
    }

    fun onSignInFail() {

        view.showSignInError()
    }
}