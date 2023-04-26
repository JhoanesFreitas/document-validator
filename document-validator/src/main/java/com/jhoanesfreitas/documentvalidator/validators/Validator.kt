package com.jhoanesfreitas.documentvalidator.validators

interface Validator {
    fun validate(document: CharSequence): Boolean
    fun validate(document: String): Boolean
}