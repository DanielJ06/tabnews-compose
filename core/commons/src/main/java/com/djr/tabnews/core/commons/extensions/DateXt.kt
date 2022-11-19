package com.djr.tabnews.core.extensions

import com.djr.tabnews.core.extensions.Constants.DATE_API
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(from: String = DATE_API, to: String = "dd/MM/yyyy"): String = try {
    val fromFormat = SimpleDateFormat(from, Locale.getDefault()).also { it.isLenient = false }
    val date = fromFormat.parse(this)
    val formatTo = SimpleDateFormat(to, Locale.getDefault())
    formatTo.format(date)
} catch (e: Exception) {
    this
}

object Constants {
    const val DATE_API = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val USUAL_DATE = "dd MMM"
}