package com.delizarov.ksmartdiet.data.impl

import android.content.Context
import android.content.SharedPreferences
import com.delizarov.ksmartdiet.data.UserRepository
import com.delizarov.ksmartdiet.domain.UserInfoNotFoundException
import com.delizarov.ksmartdiet.domain.models.UserInfo
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(ctx: Context) : UserRepository {
    private val preferences: SharedPreferences

    init {

        this.preferences = ctx.getSharedPreferences(USER_AUTH_PREFS, Context.MODE_PRIVATE)
    }

    override fun getUserInfo(): Observable<UserInfo> =
            Observable.create {

                val displayName = preferences.getString(DISPLAY_NAME_KEY, null)

                if (displayName == null)
                    it.onError(UserInfoNotFoundException())
                else {

                    val photoUrl = preferences.getString(PHOTO_URL_KEY, null)
                    it.onNext(UserInfo(displayName, photoUrl))
                }

                it.onComplete()
            }

    override fun saveUserInfo(userInfo: UserInfo?): Observable<Void> =
            Observable.create {
                preferences
                        .edit()
                        .putString(DISPLAY_NAME_KEY, userInfo!!.displayName)
                        .putString(PHOTO_URL_KEY, userInfo.photoUrl)
                        .apply()

                it.onComplete()
            }

    override fun saveIdToken(token: String): Observable<Void> =
            Observable.create {

                preferences
                        .edit()
                        .putString(TOKEN_ID_KEY, token)
                        .apply()

                it.onComplete()
            }

    override fun clearUserData() {
        preferences
                .edit()
                .clear()
                .apply()
    }

    companion object {

        private const val USER_AUTH_PREFS = "user_prefs"

        private const val TOKEN_ID_KEY = "token_id"
        private const val DISPLAY_NAME_KEY = "display_name_key"
        private const val PHOTO_URL_KEY = "photo_url_key"
    }
}