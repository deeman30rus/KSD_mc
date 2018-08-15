package com.delizarov.ksmartdiet.domain.models

import java.math.BigDecimal

data class Recipe(
        val id: Long?,
        val title: String,
        val description: String,
        val picturesURIs: List<String>,
        val videoUrls: List<String>,
        val cookTime: Int,
        val calories: Int,
        val tags: Set<String>,
        val proteins: BigDecimal,
        val triglycerides: BigDecimal,
        val carbohydrates: BigDecimal,
        val ingredients: List<Ingredient>,
        val directions: List<Direction>
)