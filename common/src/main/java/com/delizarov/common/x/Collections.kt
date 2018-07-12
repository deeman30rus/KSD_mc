package com.delizarov.common.x

import java.util.*

inline fun <E> Collection<E>.pickRandom(): E = elementAt(Random().nextInt(size))