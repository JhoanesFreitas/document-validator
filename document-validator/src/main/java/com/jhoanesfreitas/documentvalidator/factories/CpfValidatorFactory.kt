package com.jhoanesfreitas.documentvalidator.factories

import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.cpf.CpfValidator

class CpfValidatorFactory : DocumentValidatorFactory() {
    override fun getDocumentValidator(): Validator {
        return CpfValidator()
    }
}