package com.jhoanesfreitas.documentvalidator.validators.utils

fun String.removeSymbols(): String {
    return this.replace(".", "")
        .replace("-", "").replace("(", "")
        .replace(")", "").replace("/", "")
        .replace(" ", "").replace("*", "")
}