package com.jhoanesfreitas.documentvalidator.validators.cnpj

import com.jhoanesfreitas.documentvalidator.validators.Validator

class CnpjValidator : Validator {
    override fun validate(document: CharSequence): Boolean {
        return validate(document.toString())
    }

    override fun validate(document: String): Boolean {
        return false
    }
}