package com.delizarov.ksmartdiet.data.impl

import com.delizarov.common.x.bigDecimal
import com.delizarov.ksmartdiet.domain.models.*
import com.delizarov.ksmartdiet.domain.models.Unit
import java.math.BigDecimal

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
                        Grocery("Пшеничная мука"),
                        BigDecimal(120),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Молоко"),
                        BigDecimal(110),
                        Unit.MilliLiter
                ),
                Ingredient(
                        Grocery("Сахар"),
                        BigDecimal(1),
                        Unit.TableSpoon
                ),
                Ingredient(
                        Grocery("Яйцо куриное"),
                        BigDecimal(2),
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Соль"),
                        BigDecimal(0.3),
                        Unit.TeaSpoon
                ),
                Ingredient(
                        Grocery("Гашёная сода"),
                        BigDecimal(0.2),
                        Unit.TeaSpoon
                ),
                Ingredient(
                        Grocery("Молотая корица"),
                        BigDecimal(1),
                        Unit.Optional
                )
        ),
        listOf(
                Direction(1, "Отделить белки от желтков. Белки убрать в холодильник, а желтки выложить в глубокую и широкую миску, добавить к ним сахар, пару щепоток моло- той корицы и растереть до однородной массы."),
                Direction(2, "Влить небольшой струйкой в миску полный стакан молока и смешать с яично-сахарной смесью венчиком, стараясь при этом не взбивать жидкость до образования пены."),
                Direction(3, "Аккуратно всыпать в яично-молочную смесь просеянную муку, не переставая помешивать венчиком. Добавить соду, гашенную уксусом. Осторожно перемешать."),
                Direction(4, "Вымесить венчиком однородное густое тесто, по возможности максимально разбивая образовавшиеся комочки. По консистенции масса должна напоминать густую сметану."),
                Direction(5, "В охлажденные белки добавить щепотку соли и взбить венчиком в крутую пену. Если под рукой есть миксер, то лучше воспользоваться им — на высоких оборотах весь процесс займет не больше минуты."),
                Direction(6, "Бережно, силиконовой лопаткой или деревянной ложкой, постепенно вмешать взбитые белки в тесто, стараясь не повредить их хрупкую текстуру. Широкую сковороду с антипригарным покрытием смазать половиной чайной ложки сливочного масла и разогреть на умеренном огне."),
                Direction(7, "Вылить на сковороду два полных половника теста — один половник на один блинчик. Печь примерно полторы минуты, затем перевернуть и подрумянить с другой стороны. Переложить на блюдо, а на сковороду вылить следующую порцию теста."),
                Direction(8, "Добавлять в сковороду масло при этом больше не нужно: все остальные блинчики должны не жариться, а печься на слабом огне до приобретения легкого равномерного румянца. Готовые панкейки выложить на блюдо, смазать оставшимся сливочным маслом и сразу подавать.")
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
                        Grocery("Молоко"),
                        BigDecimal(450),
                        Unit.MilliLiter
                ),
                Ingredient(
                        Grocery("Овсяные хлопья"),
                        BigDecimal(75),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Сливочное масло"),
                        BigDecimal(30),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Сахар"),
                        BigDecimal(2.5),
                        Unit.TableSpoon
                ),
                Ingredient(
                        Grocery("Соль"),
                        BigDecimal(1),
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Банан"),
                        BigDecimal(1),
                        Unit.Piece
                )
        ),
        listOf(
                Direction(1, "Нагреваем в кастрюльке молоко."),
                Direction(2, "Как только молоко закипело, добавляем соль, сахар."),
                Direction(3, "Засыпаем хлопья или цельные зерна крупы."),
                Direction(4, "Далее варим кашу на среднем огне, постоянно помешивая. Время приготовления зависит от выбранного сорта овсянки. Для геркулесовых хлопьев потребуется 10 минут после закипания, для цельных зерен 30 минут."),
                Direction(5, "Когда каша будет готова, добавляем масло, нарезанный полукольцами банан,  размешиваем и даем постоять еще 5 минут.")
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
                        Grocery("Яйца"),
                        BigDecimal(3),
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Бекон"),
                        BigDecimal(4),
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Сыр"),
                        BigDecimal(50),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Соль"),
                        BigDecimal(0.3),
                        Unit.TeaSpoon
                ),
                Ingredient(
                        Grocery("Свежемолотый перец"),
                        BigDecimal(10),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Молоко"),
                        BigDecimal(40),
                        Unit.MilliLiter
                ),
                Ingredient(
                        Grocery("Масло сливочное"),
                        BigDecimal(10),
                        Unit.Gram
                )
        ),
        listOf(
                Direction(1, "Разбить яйца в посуду, и разбить желтки"),
                Direction(2, "Натереть в разбитые яйца сыр и размешать"),
                Direction(3, "На разогретую сковороду добавить сливочного масла и подождать до растопления"),
                Direction(4, "Обжарить бекон на сливочном масле до хрустящей корочки, обильно посолив и поперчив его"),
                Direction(5, "Залить бекон, яично-сырной смесью и жарить на несильном газу, до схватывания яиц"),
                Direction(6, "После того как яичница снизу схватилась, потереть сверху ещё сыр и накрыть крышкой. Жарить так ещё минут 5")
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
                        Grocery("Сосиски"),
                        BigDecimal(3),
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Варёный картофель"),
                        BigDecimal(400),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Масло расительное"),
                        BigDecimal(40),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Соль"),
                        BigDecimal(0.5),
                        Unit.TeaSpoon
                )
        ),
        listOf(
                Direction(1, "Нарезать картофель мелким кубиком"),
                Direction(2, "Нагреть сковородку с растительным маслом, высыпать на неё картофель"),
                Direction(3, "Жарить картофель до образования корочи"),
                Direction(4, "Добавить сосиски и жарить ещё минут 10")
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
                        Grocery("Яйцо куриное"),
                        BigDecimal(1),
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Мягкий творог"),
                        BigDecimal(110),
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Пшеничная мука"),
                        BigDecimal(2.5),
                        Unit.TableSpoon
                ),
                Ingredient(
                        Grocery("Сахар"),
                        BigDecimal(1),
                        Unit.Optional
                )
        ),
        listOf(
                Direction(1, "В широкую миску выложить творог, добавить яйцо, сахар и муку и вилкой смешать до однородной консистенции. Если масса получилась слишком липкой, добавить еще муки, но не больше половины столовой ложки."),
                Direction(2, "Посыпать стол небольшим количеством муки и выложить творожную массу. Разделить на две равные части и из каждой скатать колбаски одинаковой толщины."),
                Direction(3, "Острым ножом нарезать колбаски на небольшие одинаковые кусочки. При желании можно слегка приплюснуть затем каждый кусочек и придать ему округлую форму."),
                Direction(4, "В небольшой кастрюле довести до кипения воду. Аккуратно один за другим опустить вареники в кипящую воду. Варить, слегка помешивая шумовкой, пока все вареники не всплывут на поверхность, плюс еще минуту."),
                Direction(5, "Готовые вареники выложить из кастрюли на тарелки и полить вареньем, например абрикосовым. Подавать их можно горячими или теплыми.")

        )
)

val waffles = Recipe(
        6L,
        "Венские вафли",
        "Невероятно вкусные, всеми любимые венские мягкие вафли можно приготовить в электровафельнице в домашних условиях, рецепты и пошаговые фото помогут Вам!",
        emptyList(),
        emptyList(),
        30,
        300,
        setOf("сладкое", "печёное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(
                Ingredient(
                        Grocery("Мука пшеничная"),
                        175.bigDecimal,
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Молоко"),
                        100.bigDecimal,
                        Unit.MilliLiter
                ),
                Ingredient(
                        Grocery("Масло сливочное"),
                        75.bigDecimal,
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Яйца"),
                        2.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Сахар"),
                        50.bigDecimal,
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Разрыхлитель"),
                        5.bigDecimal,
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Сок лимонный"),
                        5.bigDecimal,
                        Unit.MilliLiter
                ),
                Ingredient(
                        Grocery("Соль"),
                        1.bigDecimal,
                        Unit.Optional
                )
        ),
        listOf(
                Direction(1, "Масло размягчить и растереть с сахаром"),
                Direction(2, "Добавить молоко и яйца и размешать венчиком"),
                Direction(3, "Добавить муку, разрыхлитель, соль и лимонный сок. Тщательно перемешать до однородной массы"),
                Direction(4, "Установить панели. Включить Мультипекарь, закрыть крышку"),
                Direction(5, "Когда загорится зелёный индикатор, открыть крышку, выложить тесто в формы, закрыть крышку, выпекать 5-6 минут.")
        )
)

val sandwitches = Recipe(
        7L,
        "Сэндвич с яичницей, беконом и сыром",
        "Сэндвич — блюдо, состоящее из двух или нескольких ломтиков хлеба и одного или нескольких слоёв мяса и/или других начинок.",
        emptyList(),
        emptyList(),
        30,
        300,
        setOf("солёное", "жаренное"),
        30.bigDecimal,
        40.bigDecimal,
        50.bigDecimal,
        listOf(
                Ingredient(
                        Grocery("Бекон"),
                        4.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Тостовый хлеб"),
                        4.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Сыр эдам"),
                        50.bigDecimal,
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Помидоры"),
                        1.bigDecimal,
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Горчица"),
                        1.bigDecimal,
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Кетчуп"),
                        1.bigDecimal,
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Сливочное масло"),
                        30.bigDecimal,
                        Unit.Gram
                ),
                Ingredient(
                        Grocery("Яйцо куриное"),
                        2.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Листья латука"),
                        4.bigDecimal,
                        Unit.Piece
                )
        ),
        listOf(
                Direction(1, "Поджарить тонкие ломтики бекона до золотистой корочки."),
                Direction(2, "Подогреть хлеб и одну половину сэндвича намазать кетчупом или горчицей (можно приготовить самостоятельно), на вторую положить сыр, ломтики помидоров, бекон и латук."),
                Direction(3, "Тем временем на сливочном масле поджарить небольшую глазунью — такую, чтобы желток был полуготовым."),
                Direction(4, "Выложить глазунью на латук, закрыть другой половинкой сэндвича и подавать.")
        )
)

val toasts = Recipe(
        8L,
        "Французские тосты",
        "Классические французские тосты",
        emptyList(),
        emptyList(),
        30,
        510,
        setOf("Сладкое", "жаренное"),
        13.bigDecimal,
        26.bigDecimal,
        52.bigDecimal,
        listOf(
                Ingredient(
                        Grocery("Городская булка"),
                        1.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Молоко"),
                        3.bigDecimal,
                        Unit.TableSpoon
                ),
                Ingredient(
                        Grocery("Яйцо куриное"),
                        2.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Соль"),
                        BigDecimal(0.5),
                        Unit.TeaSpoon
                ),
                Ingredient(
                        Grocery("Сахар"),
                        1.bigDecimal,
                        Unit.TableSpoon
                )
        ),
        listOf(
                Direction(1, "Порезать хлеб"),
                Direction(2, "Взбить яйца с молоком, сахаром и солью."),
                Direction(3, "Нагреть сковороду и растопить сливочное масло."),
                Direction(4, "Обмакнуть хлеб с двух сторон в смеси и отправить на сковороду."),
                Direction(5, "Жарить по полторы минуты с каждой стороны (учитывайте особенности вашей плиты)."),
                Direction(6, "Разогреть духовку до 180 градусов и отправить тосты допекаться в духовке до корочки.")
        )
)

val boiledEggs = Recipe(
        9L,
        "Варёные яйца",
        "Обычные варёные яйца",
        emptyList(),
        emptyList(),
        30,
        510,
        setOf("солёное", "варёное"),
        13.bigDecimal,
        26.bigDecimal,
        52.bigDecimal,
        listOf(
                Ingredient(
                        Grocery("Сосиски"),
                        3.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Помидоры"),
                        1.bigDecimal,
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Яйцо куриное"),
                        3.bigDecimal,
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Огурец"),
                        1.bigDecimal,
                        Unit.Optional
                ),
                Ingredient(
                        Grocery("Булка городская"),
                        BigDecimal(0.3),
                        Unit.Piece
                ),
                Ingredient(
                        Grocery("Горошек"),
                        BigDecimal(3),
                        Unit.TableSpoon
                )
        ),
        listOf(
                Direction(1, "Поставить яйца в холодную воду на огонь."),
                Direction(2, "Как только вода закипит добавить к яйцам сосиски. Варить 9 минут"),
                Direction(3, "Из хлеба нарезать бутерброды со сливочным маслом"),
                Direction(4, "Помидор и огурец нарезать.")
        )
)

