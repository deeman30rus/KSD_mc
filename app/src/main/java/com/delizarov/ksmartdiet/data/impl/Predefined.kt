package com.delizarov.ksmartdiet.data.impl

import com.delizarov.common.x.bigDecimal
import com.delizarov.ksmartdiet.domain.models.Grocery
import com.delizarov.ksmartdiet.domain.models.Ingredient
import com.delizarov.ksmartdiet.domain.models.Recipe
import com.delizarov.ksmartdiet.domain.models.Unit
import java.math.BigDecimal

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
        listOf(
                Ingredient(
                        Grocery("Сосисочки"),
                        BigDecimal(20),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Иички"),
                        BigDecimal(20),
                        Unit.Kilo
                ),
                Ingredient(
                        Grocery("Мяско"),
                        BigDecimal(20),
                        Unit.Liter
                ),
                Ingredient(
                        Grocery("Сахарок"),
                        BigDecimal(20),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Морковка"),
                        BigDecimal(20),
                        Unit.MilliLiter
                )
        ),
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
        listOf(
                Ingredient(
                        Grocery("Сосисочки"),
                        BigDecimal(20),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Иички"),
                        BigDecimal(20),
                        Unit.Kilo
                ),
                Ingredient(
                        Grocery("Мяско"),
                        BigDecimal(20),
                        Unit.Liter
                ),
                Ingredient(
                        Grocery("Сахарок"),
                        BigDecimal(20),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Морковка"),
                        BigDecimal(20),
                        Unit.MilliLiter
                )
        ),
        listOf()
)

val oatmeal = Recipe(
        2L,
        "Овсяная каша",
        "Описание",
        listOf(),
        listOf(),
        25,
        300,
        setOf("Сладкое", "Печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(
                Ingredient(
                        Grocery("Сосисочки"),
                        BigDecimal(20),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Иички"),
                        BigDecimal(20),
                        Unit.Kilo
                ),
                Ingredient(
                        Grocery("Мяско"),
                        BigDecimal(20),
                        Unit.Liter
                ),
                Ingredient(
                        Grocery("Сахарок"),
                        BigDecimal(20),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Морковка"),
                        BigDecimal(20),
                        Unit.MilliLiter
                )
        ),
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
        listOf(
                Ingredient(
                        Grocery("Сосисочки"),
                        BigDecimal(20),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Иички"),
                        BigDecimal(20),
                        Unit.Kilo
                ),
                Ingredient(
                        Grocery("Мяско"),
                        BigDecimal(20),
                        Unit.Liter
                ),
                Ingredient(
                        Grocery("Сахарок"),
                        BigDecimal(20),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Морковка"),
                        BigDecimal(20),
                        Unit.MilliLiter
                )
        ),
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
        listOf(
                Ingredient(
                        Grocery("Сосисочки"),
                        BigDecimal(20),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Иички"),
                        BigDecimal(20),
                        Unit.Kilo
                ),
                Ingredient(
                        Grocery("Мяско"),
                        BigDecimal(20),
                        Unit.Liter
                ),
                Ingredient(
                        Grocery("Сахарок"),
                        BigDecimal(20),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Морковка"),
                        BigDecimal(20),
                        Unit.MilliLiter
                )
        ),
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
        listOf(
                Ingredient(
                        Grocery("Сосисочки"),
                        BigDecimal(20),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Иички"),
                        BigDecimal(20),
                        Unit.Kilo
                ),
                Ingredient(
                        Grocery("Мяско"),
                        BigDecimal(20),
                        Unit.Liter
                ),
                Ingredient(
                        Grocery("Сахарок"),
                        BigDecimal(20),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Морковка"),
                        BigDecimal(20),
                        Unit.MilliLiter
                )
        ),
        listOf()
)