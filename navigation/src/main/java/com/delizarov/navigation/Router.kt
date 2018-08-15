package com.delizarov.navigation

interface Router {

    fun forwardTo(screenKeyHolder: ScreenKeyHolder)

    fun back()
}