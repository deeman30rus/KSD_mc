package com.delizarov.ksmartdiet.domain.models

data class Recipe(
        val id: Long?,
        val title: String,
        val cookTime: Int,
        val calories: Int,
        val tags: Set<String>
)