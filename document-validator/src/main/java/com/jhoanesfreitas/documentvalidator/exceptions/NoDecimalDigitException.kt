package com.jhoanesfreitas.documentvalidator.exceptions

class NoDecimalDigitException(override val message: String = "Um dos dígitos não é um decimal.") :
    IllegalArgumentException()