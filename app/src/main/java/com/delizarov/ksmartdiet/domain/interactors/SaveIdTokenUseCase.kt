package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SaveIdTokenUseCase @Inject constructor(
        private val userRepository: UserRepository
) : UseCase<Void, String>() {


    override fun createObservable(params: String?): Observable<Void> =
            userRepository
                    .saveIdToken(params!!)
                    .subscribeOn(Schedulers.newThread())

}