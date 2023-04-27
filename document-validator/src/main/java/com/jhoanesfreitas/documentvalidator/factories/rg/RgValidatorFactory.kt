package com.jhoanesfreitas.documentvalidator.factories.rg

import com.jhoanesfreitas.documentvalidator.factories.DocumentValidatorFactory
import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.rg.RgValidator

class RgValidatorFactory : DocumentValidatorFactory() {
    override fun getDocumentValidator(): Validator {
        return RgValidator()
    }
}