package com.djr.tabnews.core.commons.extensions

fun String.toCapitalized(): String {
    return this.replaceFirstChar { str -> str.uppercase() }
}

fun String.isEmail() = RegexEnum.EMAIL.match(this)
fun String.isNotEmail() = !this.isEmail()