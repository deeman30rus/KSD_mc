package com.delizarov.ksmartdiet.domain

class UserInfoNotFoundException : Exception("No logged in user found")

class DietSettingsNotFoundException: Exception("Diet settings not found")