package com.delizarov.ksmartdiet.data

import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.Observable

interface UserRepository {

    fun getUserInfo(): Observable<UserInfo>

    fun saveUserInfo(userInfo: UserInfo?): Observable<Void>

    fun saveIdToken(token:String): Observable<Void>
}