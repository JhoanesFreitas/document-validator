package com.jhoanesfreitas.documentvalidator.validators.utils

import com.jhoanesfreitas.documentvalidator.exceptions.DocumentNumberSizeException

fun Int.checkNumberSize(documentNumberSize: Int) {
    if (this != documentNumberSize) throw DocumentNumberSizeException()
}