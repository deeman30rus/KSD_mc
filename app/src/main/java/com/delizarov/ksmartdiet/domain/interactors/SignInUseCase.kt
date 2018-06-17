package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.Observable

class SignInUseCase : UseCase<UserInfo, Void>() {
    override fun createObservable(params: Void?): Observable<UserInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}