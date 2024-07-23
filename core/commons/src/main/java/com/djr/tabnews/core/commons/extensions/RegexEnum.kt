package com.djr.tabnews.core.commons.extensions

enum class RegexEnum(val value: Regex) {
    EMAIL("""^[a-z0-9_%+-]+([.-][a-z0-9]+)*@[a-z0-9]+([.-][a-z0-9]+)*\.[a-z]{2,3}$""".toRegex());

    fun match(string: String) = this.value.containsMatchIn(string)
}
