package com.delizarov.ksmartdiet.domain.interactors

import com.delizarov.ksmartdiet.data.DietRepository
import com.delizarov.ksmartdiet.domain.models.Meal
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import javax.inject.Inject

sealed class MealReadParams

class DateParams(
        val date: DateTime
) : MealReadParams()

class GetMealUseCase @Inject constructor(
        private val dietRepository: DietRepository
) : UseCase<Meal, MealReadParams>() {

    override fun createObservable(params: MealReadParams?): Observable<Meal> =
            readMealObservable(params)
                    .subscribeOn(Schedulers.newThread())

    private fun readMealObservable(params: MealReadParams?) =
            when (params) {
                is DateParams -> dietRepository.readMealsForDate(params.date)
                else -> Observable.empty<Meal>()
            }

}