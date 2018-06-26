package com.delizarov.ksmartdiet.data

import com.delizarov.ksmartdiet.domain.models.DietSettings
import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.Observable

interface UserRepository {

    fun getUserInfo(): Observable<UserInfo>

    fun saveUserInfo(userInfo: UserInfo?): Observable<Void>

    fun saveIdToken(token:String): Observable<Void>
}

interface DietRepository {

    fun getDietSettings(): Observable<DietSettings>

    fun saveDietSettings(dietSettings: DietSettings) : Observable<Any>
}