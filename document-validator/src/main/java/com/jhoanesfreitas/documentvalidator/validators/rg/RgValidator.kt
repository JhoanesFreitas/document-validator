package com.jhoanesfreitas.documentvalidator.validators.rg

import com.jhoanesfreitas.documentvalidator.validators.Validator

class RgValidator : Validator {
    override fun validate(document: CharSequence): Boolean {
        return validate(document.toString())
    }

    override fun validate(document: String): Boolean {
        return false
    }
}