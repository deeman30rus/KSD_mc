package com.delizarov.common.x

internal val colors = listOf(
        -0x1a8c8d, // red_300
        -0xbbcca, // red 500
        -0x2cd0d1, // red 700
        -0xf9d6e, // pink 300
        -0x16e19d, // pink 500
        -0x3de7a5, // pink 700
        -0x459738, // purple 300
        -0x63d850, // purple 500
        -0x84e05e, // purple 700
        -0x6a8a33, // deep purple 300
        -0x98c549, // deep purple 500
        -0xaed258, // deep purple 700
        -0x867935, // indigo
        -0xc0ae4b,
        -0xcfc061,
        -0x9b4a0a, // blue
        -0xde690d,
        -0xe6892e,
        -0xb03c09, // light blue
        -0xfc560c,
        -0xfd772f,
        -0xb22f1f, // cyan
        -0xff432c,
        -0xff6859,
        -0xb24954, // teal
        -0xff6978,
        -0xff8695,
        -0x7e387c, // green
        -0xb350b0,
        -0xc771c4,
        -0x512a7f, // light green
        -0x743cb6,
        -0x9760c8,
        -0x23188b, // lime
        -0x3223c7,
        -0x504bd5,
        -0xe8a, // yellow
        -0x14c5,
        -0x43fd3,
        -0x2ab1, // amber
        -0x3ef9,
        -0x6000,
        -0x48b3, // orange
        -0x6800,
        -0xa8400,
        -0x759b, // deep orange
        -0xa8de,
        -0x19b5e7
)

val String.decodedColor: Int
    get() {
        val index = if (hashCode() < 0) -hashCode() else hashCode()

        return colors[index % colors.size]
    }