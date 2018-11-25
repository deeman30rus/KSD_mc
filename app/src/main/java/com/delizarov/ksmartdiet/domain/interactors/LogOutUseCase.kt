package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.data.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
        private val dietRepository: DietRepository,
        private val userRepository: UserRepository
) : UseCase<Unit, Unit>() {

    override fun createObservable(params: Unit?) = Observable.fromCallable {

        userRepository.clearUserData()
        dietRepository.clearDietData()
    }.subscribeOn(Schedulers.io())

}