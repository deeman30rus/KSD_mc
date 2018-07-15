package com.delizarov.navigation

import android.support.v4.app.Fragment

abstract class FragmentFactory {

    abstract fun <T, R> create(screenKey: String, data1: T?, data2: R?): Fragment

    fun <T> create(screenKey: String, data: T?) = create(screenKey, data, null)

    fun create(screenKey: String) = create(screenKey, null)
}