package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.domain.models.Recipe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
        private val dietRepository: DietRepository
) : UseCase<Recipe, Long>() {


    override fun createObservable(params: Long?) =
            Observable.fromCallable {
                dietRepository.getRecipeById(params!!)
            }.subscribeOn(Schedulers.newThread())

}