package com.jhoanesfreitas.documentvalidator.validators.cnpj

import com.jhoanesfreitas.documentvalidator.BuildConfig.*
import com.jhoanesfreitas.documentvalidator.factories.DocumentValidatorFactory
import com.jhoanesfreitas.documentvalidator.factories.cnpj.CnpjValidatorFactory
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

    @Test
    fun cnpjValidator_validateCnpj_verifyIfCnpjIsInvalidAfterPassInvalidNumber() {
        val cnpjValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cnpjValidator.validate("1as.ded.oew/oooi-qo")

        Assert.assertFalse("O Cnpj é válido mesmo passando uma string com letras!", isDocumentValid)
    }

    @Test
    fun cnpjValidator_validateCnpj_verifyIfCnpjIsInvalidAfterPassEmptyString() {
        val cnpjValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cnpjValidator.validate("")

        Assert.assertFalse("O Cnpj é válido mesmo passando uma string vazia!", isDocumentValid)
    }

    @Test
    fun cnpjValidator_validateCnpj_verifyIfCnpjIsInvalidAfterPassIncompleteNumber() {
        val cnpjValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cnpjValidator.validate(FAKE_INCOMPLETE_CNPJ)

        Assert.assertFalse("O Cnpj é válido mesmo passando um CNPJ incompleto!", isDocumentValid)
    }
}