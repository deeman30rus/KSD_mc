package com.delizarov.ksmartdiet.data.impl

import com.delizarov.common.x.bigDecimal
import com.delizarov.ksmartdiet.domain.models.*
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
        listOf(
                Direction("Значит, готовим еду. Я беру сковороду.", 1),
                Direction("Потом на сковороду я эту, мать ее, курицу кладу.", 2),
                Direction("Потом туда в догонку ей подсолнечного масла", 3),
                Direction("Еще немного перца и протертой сырной массы.", 4),
                Direction("Потом потру морковки и покрошу лучку,", 5),
                Direction("А чтоб не мучал запах, можно врезать коньячку.", 6)
        )
)

val pancakes = Recipe(
        1L,
        "Американские блинчики с корицей",
        "Панкейки — американские пышные блинчики, которые похожи одновременно и на наши блины и на оладьи. Но от первых они отличаются меньшими размерами и большей толщиной, а от вторых — тем, что жарятся не в огромном количестве масла, а почти на сухой сковороде. Об этом сообщает Рамблер.",
        listOf(),
        listOf(),
        25,
        300,
        setOf("сладкое", "печёное"),
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
        listOf(
                Direction("Значит, готовим еду. Я беру сковороду.", 1),
                Direction("Потом на сковороду я эту, мать ее, курицу кладу.", 2),
                Direction("Потом туда в догонку ей подсолнечного масла", 3),
                Direction("Еще немного перца и протертой сырной массы.", 4),
                Direction("Потом потру морковки и покрошу лучку,", 5),
                Direction("А чтоб не мучал запах, можно врезать коньячку.", 6)
        )
)

val oatmeal = Recipe(
        2L,
        "Овсяная каша",
        "Овсяная каша или овсянка – это каша, которую делают из овсяных хлопьев (раздавленных зерен) или овсяной муки. Она может быть густой и жидкой. Очень жидкую кашу иногда даже считают овсяным супом, но называют тоже овсянкой",
        listOf(),
        listOf(),
        25,
        300,
        setOf("варёное", "сладкое"),
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
        listOf(
                Direction("Значит, готовим еду. Я беру сковороду.", 1),
                Direction("Потом на сковороду я эту, мать ее, курицу кладу.", 2),
                Direction("Потом туда в догонку ей подсолнечного масла", 3),
                Direction("Еще немного перца и протертой сырной массы.", 4),
                Direction("Потом потру морковки и покрошу лучку,", 5),
                Direction("А чтоб не мучал запах, можно врезать коньячку.", 6)
        )
)

val friedEggs = Recipe(
        3L,
        "Яичница",
        "Яичница - это блюдо, приготовленное из яиц, перемешиваемых или избитых вместе в кастрюле при мягком нагревании, обычно с солью и маслом и различными другими ингредиентами.",
        listOf(),
        listOf(),
        25,
        300,
        setOf("жаренное", "яйца"),
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
        listOf(
                Direction("Значит, готовим еду. Я беру сковороду.", 1),
                Direction("Потом на сковороду я эту, мать ее, курицу кладу.", 2),
                Direction("Потом туда в догонку ей подсолнечного масла", 3),
                Direction("Еще немного перца и протертой сырной массы.", 4),
                Direction("Потом потру морковки и покрошу лучку,", 5),
                Direction("А чтоб не мучал запах, можно врезать коньячку.", 6)
        )
)

val friedPotatoes = Recipe(
        4L,
        "Картошка",
        "Жареный картофель, жаренная картошка — кусочки картофеля «соломкой» или «кружочками», обжаренные на сковороде в относительно небольшом количестве растительного масла.",
        listOf(),
        listOf(),
        25,
        300,
        setOf("жареное", "картофель"),
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
        listOf(
                Direction("Значит, готовим еду. Я беру сковороду.", 1),
                Direction("Потом на сковороду я эту, мать ее, курицу кладу.", 2),
                Direction("Потом туда в догонку ей подсолнечного масла", 3),
                Direction("Еще немного перца и протертой сырной массы.", 4),
                Direction("Потом потру морковки и покрошу лучку,", 5),
                Direction("А чтоб не мучал запах, можно врезать коньячку.", 6)
        )
)

val lazies = Recipe(
        5L,
        "Завтрак для ленивых",
        "Очередной способ замаскировать творожок и накормить им наших деток;))) Этот рецепт живет в нашей семье очень давно, из книги «Кулинарные рецепты», 1956 года выпуска, правда со временем я немного его изменила, заменив часть муки на манную крупу… Галушки получаются очень-очень нежные, детки их любят!",
        listOf(),
        listOf(),
        25,
        300,
        setOf("сладкое", "варёное"),
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
        listOf(
                Direction("Значит, типа, готовим еду. Я беру сковороду.", 1),
                Direction("Потом на сковороду я эту, мать ее, курицу кладу.", 2),
                Direction("Потом туда в догонку ей подсолнечного масла", 3),
                Direction("Еще немного перца и протертой сырной массы.", 4),
                Direction("Потом потру морковки и покрошу лучку,", 5),
                Direction("А чтоб не мучал запах, можно врезать коньячку.", 6)
        )
)