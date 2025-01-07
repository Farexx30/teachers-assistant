package com.example.teachersassistant.extensions

fun String.toTitleCase(): String {
    return this.lowercase()
        .replaceFirstChar { it.uppercase() }
}


