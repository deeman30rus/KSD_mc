package com.delizarov.ksmartdiet.domain.models

class Ration(
        val name: String,
        val description: String,
        val recipes: Set<Recipe>
)