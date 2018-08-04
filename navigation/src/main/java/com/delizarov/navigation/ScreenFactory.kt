package com.delizarov.navigation

abstract class ScreenFactory {


    abstract fun <T, R, S> createScreen(screenKey: ScreenKey, data1: T?, data2: R?, data3: S?): ScreenKeyHolder

    fun <T, R> createScreen(screenKey: ScreenKey, data1: T?, data2: R?) = createScreen(screenKey, data1, data2, null)

    fun <T> createScreen(screenKey: ScreenKey, data1: T?) = createScreen(screenKey, data1, null)

    fun createScreen(screenKey: ScreenKey) = createScreen(screenKey, null)
}