package com.delizarov.ksmartdiet.data

import com.delizarov.ksmartdiet.domain.models.*
import io.reactivex.Observable
import org.joda.time.DateTime

interface UserRepository {

    fun getUserInfo(): Observable<UserInfo>

    fun saveUserInfo(userInfo: UserInfo?): Observable<Void>

    fun saveIdToken(token: String): Observable<Void>
}

interface DietRepository {

    fun getDietSettings(): Observable<DietSettings>

    fun writeDietSettings(dietSettings: DietSettings): Observable<Any>

    /**
     * Возвращает список всех приёмов пищи для выбранного дня
     *
     * @param date: выбранный день
     *
     * @return список объектов приёма пищи
     * */
    fun getMealsForDate(date: DateTime): List<Meal>

    /**
     * Возвращает список приёмов пищи для выбранного периода с заданным типом приёма пищи
     *
     * @param dateFrom - дата начала периода выборки (берётся начало дня указанного периода)
     * @param dateTo - дата окончания периода выборкт (берётся окончание дня указанного периода)
     * @param mealType - фильтр по типу приёма пищи, если null, то в выборку попадут все приёмы пищи
     *
     * @return список объектов приёма пищи
     * */
    fun getMealsForPeriod(dateFrom: DateTime, dateTo: DateTime, mealType: MealType?): List<Meal>

    /**
     * Получить выбранный рацион
     *
     * @return объект рациона
     * */
    fun getCurrentRation(): Ration

    fun readRation(): List<Ration>

    /**
     * Записать приём пищи
     *
     * @param meal: объект приёма пищи
     * */
    fun writeMeal(meal: Meal)
}