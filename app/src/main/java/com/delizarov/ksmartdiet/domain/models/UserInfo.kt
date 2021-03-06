package com.delizarov.ksmartdiet.domain.models

import java.io.Serializable

class UserInfo(
        val displayName: String,
        val photoUrl: String?
) : Serializable