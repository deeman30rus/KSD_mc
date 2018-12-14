package com.delizarov.navigation.android

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.delizarov.navigation.Router
import com.delizarov.navigation.ScreenKeyHolder

class FragmentRouter(
        private val fm: FragmentManager,
        @IdRes private val containerId: Int
) : Router {

    override fun replaceTo(screenKeyHolder: ScreenKeyHolder) {
        val fragment = screenKeyHolder as Fragment

        fm
                .beginTransaction()
                .replace(containerId, fragment, screenKeyHolder.screenKey)
                .commit()
    }
}