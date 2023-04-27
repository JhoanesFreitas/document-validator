package com.jhoanesfreitas.documentvalidator.validators.cnpj

import com.jhoanesfreitas.documentvalidator.BuildConfig.FAKE_CNPJ
import com.jhoanesfreitas.documentvalidator.BuildConfig.FAKE_INVALID_CNPJ
import com.jhoanesfreitas.documentvalidator.factories.cnpj.CnpjValidatorFactory
import com.jhoanesfreitas.documentvalidator.factories.DocumentValidatorFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CnpjValidatorTest {

    private lateinit var documentValidatorFactory: DocumentValidatorFactory

    @Before
    fun setUp() {
        documentValidatorFactory = CnpjValidatorFactory()
    }

    @Test
    fun cnpjValidator_validateCnpj_verifyIfCnpjIsValid() {
        val cnpjValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cnpjValidator.validate(FAKE_CNPJ)

        Assert.assertTrue("O Cnpj é inválido!", isDocumentValid)
    }

    @Test
    fun cnpjValidator_validateCnpj_verifyIfCnpjIsInvalid() {
        val cnpjValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cnpjValidator.validate(FAKE_INVALID_CNPJ)

        Assert.assertFalse("O Cnpj é válido!", isDocumentValid)
    }
}