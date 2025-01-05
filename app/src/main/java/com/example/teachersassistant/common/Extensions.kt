package com.example.teachersassistant.common

fun String.toTitleCase(): String {
    return this.lowercase()
        .replaceFirstChar { it.uppercase() }
}