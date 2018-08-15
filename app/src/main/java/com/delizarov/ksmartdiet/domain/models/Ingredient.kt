package com.delizarov.ksmartdiet.domain.models

import java.math.BigDecimal

class Grocery(
        val name: String
)

enum class Unit {
    Kilo, // кг
    Gram, // г
    Liter, // л
    MilliLiter, // мл
    TeaSpoon, // ч. ложка
    TableSpoon, // ст. ложка
    Piece, // шт.
    Optional; // по вкусу
}

class Ingredient(
        val grocery: Grocery,
        val amount: BigDecimal,
        val unit: Unit
)