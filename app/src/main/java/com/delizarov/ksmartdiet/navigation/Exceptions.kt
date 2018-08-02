package com.delizarov.ksmartdiet.navigation

class NoSuchScreenException(screenKey: String) : Exception("No screen found with given key '$screenKey'")