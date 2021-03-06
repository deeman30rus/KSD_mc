package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.domain.models.DietSettings
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ReadDietSettingsUseCase @Inject constructor(
        private val dietRepository: DietRepository
) : UseCase<DietSettings, Void>() {


    override fun createObservable(params: Void?) =
            dietRepository.getDietSettings()
                    .subscribeOn(Schedulers.newThread())

}

class SaveDietSettingsUseCase @Inject constructor(
        private val dietRepository: DietRepository
) : UseCase<Unit, DietSettings>() {

    override fun createObservable(params: DietSettings?) = Observable.fromCallable {

        dietRepository.clearDietData()

        dietRepository.writeDietSettings(params!!)
    }.subscribeOn(Schedulers.newThread())
}