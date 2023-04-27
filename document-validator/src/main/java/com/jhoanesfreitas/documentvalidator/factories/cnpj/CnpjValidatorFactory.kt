package com.jhoanesfreitas.documentvalidator.factories.cnpj

import com.jhoanesfreitas.documentvalidator.factories.DocumentValidatorFactory
import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.cnpj.CnpjValidator

class CnpjValidatorFactory : DocumentValidatorFactory() {
    override fun getDocumentValidator(): Validator {
        return CnpjValidator()
    }
}