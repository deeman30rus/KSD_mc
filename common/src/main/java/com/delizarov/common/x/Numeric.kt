package com.delizarov.common.x

import java.math.BigDecimal

val Int.bigDecimal: BigDecimal
    get() = BigDecimal(this)