package com.djr.tabnews.core.extensions

fun String.toCapitalized(): String {
    return this.replaceFirstChar { str -> str.uppercase() }
}