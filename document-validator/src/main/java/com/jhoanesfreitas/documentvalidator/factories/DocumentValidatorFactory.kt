package com.jhoanesfreitas.documentvalidator.factories

import com.jhoanesfreitas.documentvalidator.validators.Validator

abstract class DocumentValidatorFactory {
    abstract fun getDocumentValidator(): Validator
}