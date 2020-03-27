package com.jimmyhernandez.domain

data class Title(
    val value: String? = null,
    val matchLevel: String? = null,
    val fullyHighlighted: Boolean? = null,
    val matchedWords: List<String>? = null
)