package com.djr.tabnews.core.uikit.theme.extensions

import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun String.prettyCount(): String {
    val suffix = listOf(" ", "k", "M", "B")
    val logBase10 = floor(log10(this.toFloat())).toInt()
    val base = logBase10 / 3
    return if (logBase10 >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            (this.toFloat() / 10F.pow(base * 3))
        ) + suffix[logBase10 / 3]
    } else {
        this
    }
}

fun Int.prettyCount(): String {
    val suffix = listOf(" ", "k", "M", "B")
    val logBase10 = floor(log10(this.toFloat())).toInt()
    val base = logBase10 / 3
    return if (logBase10 >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            (this / 10F.pow(base * 3))
        ) + suffix[logBase10 / 3]
    } else {
        this.toString()
    }
}