package com.delizarov.ksmartdiet.data.impl

import com.delizarov.common.x.bigDecimal
import com.delizarov.ksmartdiet.domain.models.Recipe

val default_recipe = Recipe(
        -1L,
        "default",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(),
        listOf()
)

val pancakes = Recipe(
        1L,
        "Американские блинчики с корицей",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(),
        listOf()
)

val oatmeal = Recipe(
        2L,
        "Оввяная каша",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(),
        listOf()
)

val friedEggs = Recipe(
        3L,
        "Яичница",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(),
        listOf()
)

val friedPotatoes = Recipe(
        4L,
        "Картошка",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(),
        listOf()
)

val lazies = Recipe(
        5L,
        "Картошка",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(),
        listOf()
)