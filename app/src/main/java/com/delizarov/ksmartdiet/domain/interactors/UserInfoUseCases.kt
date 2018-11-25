package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.UserRepository
import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReadUserInfoUseCase @Inject constructor(
        private val userRepository: UserRepository
) : UseCase<UserInfo, Void>() {

    override fun createObservable(params: Void?): Observable<UserInfo> =
            userRepository
                    .getUserInfo()
                    .subscribeOn(Schedulers.newThread())

}

class SaveUserInfoUseCase @Inject constructor(
        private val userRepository: UserRepository
) : UseCase<Void, UserInfo>() {


    override fun createObservable(params: UserInfo?): Observable<Void> =
            userRepository.saveUserInfo(params)
                    .subscribeOn(Schedulers.newThread())
}
