package com.delizarov.navigation

import java.util.*

typealias ScreenKey = String

abstract class NavigationController(
        private val screenFactory: ScreenFactory,
        private val router: Router
) {

    private val stack = Stack<ScreenKey>()

    fun <T> setRoot(sKey: ScreenKey, data1: T?) {

        val screen = screenFactory.createScreen(sKey, data1)

        stack.clear()
        stack.push(sKey)

        router.forwardTo(screen)
    }

    fun setRoot(sKey: ScreenKey) = setRoot(sKey, null)

    fun <T, S, R> forwardTo(sKey: ScreenKey, data1: T?, data2: S?, data3: R?) {
        val screen = screenFactory.createScreen(sKey, data1, data2, data3)

        stack.push(sKey)

        router.forwardTo(screen)
    }

    fun <T, S> forwardTo(sKey: ScreenKey, data1: T?, data2: S?) = forwardTo(sKey, data1, data2, null)

    fun <T> forwardTo(sKey: ScreenKey, data1: T?) = forwardTo(sKey, data1, null)

    fun forwardTo(sKey: ScreenKey) = forwardTo(sKey, null)

    fun <T, S, R> replaceTo(sKey: ScreenKey, data1: T?, data2: S?, data3: R?) {

        val screen = screenFactory.createScreen(sKey, data1, data2, data3)

        stack.pop()
        router.back()

        stack.push(sKey)
        router.forwardTo(screen)

    }

    fun <T, S> replaceTo(sKey: ScreenKey, data1: T?, data2: S?) = replaceTo(sKey, data1, data2, null)

    fun <T> replaceTo(sKey: ScreenKey, data1: T?) = replaceTo(sKey, data1, null)

    fun replaceTo(sKey: ScreenKey) = replaceTo(sKey, null)

    fun back() {

        stack.pop()

        if (stack.empty())
            closeNavigationTree()
        else
            router.back()
    }

    fun backTo(sKey: ScreenKey) {

        while (stack.isNotEmpty() && stack.peek() != sKey)
            back()
    }

    abstract fun closeNavigationTree()
}