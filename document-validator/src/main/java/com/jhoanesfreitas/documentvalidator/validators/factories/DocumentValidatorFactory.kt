package com.jhoanesfreitas.documentvalidator.validators.factories

import com.jhoanesfreitas.documentvalidator.validators.Validator

abstract class DocumentValidatorFactory {
    abstract fun getDocumentValidator(): Validator
}