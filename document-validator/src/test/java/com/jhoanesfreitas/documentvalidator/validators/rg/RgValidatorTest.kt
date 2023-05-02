package com.jhoanesfreitas.documentvalidator.validators.rg

import com.jhoanesfreitas.documentvalidator.BuildConfig.*
import com.jhoanesfreitas.documentvalidator.factories.DocumentValidatorFactory
import com.jhoanesfreitas.documentvalidator.factories.rg.RgValidatorFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RgValidatorTest {

    private lateinit var documentValidatorFactory: DocumentValidatorFactory

    @Before
    fun setUp() {
        documentValidatorFactory = RgValidatorFactory()
    }

    @Test
    fun rgValidator_validateRg_verifyIfRgIsValid() {
        val rgValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = rgValidator.validate(FAKE_RG)

        Assert.assertTrue("O Rg é inválido!", isDocumentValid)
    }

    @Test
    fun rgValidator_validateRg_verifyIfRgIsInValid() {
        val rgValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = rgValidator.validate(FAKE_INVALID_RG)

        Assert.assertTrue("O Rg é válido!", isDocumentValid)
    }

    @Test
    fun rgValidator_validateRg_verifyIfRgIsInvalidAfterPassInvalidNumber() {
        val rgValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = rgValidator.validate("ws.gl.mm-b")

        Assert.assertFalse("O Rg é válido mesmo passando uma string com letras!", isDocumentValid)
    }

    @Test
    fun rgValidator_validateRg_verifyIfRgIsInvalidAfterPassEmptyString() {
        val rgValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = rgValidator.validate("")

        Assert.assertFalse("O Rg é válido mesmo passando uma string vazia!", isDocumentValid)
    }

    @Test
    fun rgValidator_validateRg_verifyIfRgIsInvalidAfterPassIncompleteNumber() {
        val rgValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = rgValidator.validate(FAKE_INCOMPLETE_RG)

        Assert.assertFalse("O Rg é válido mesmo passando um RG incompleto!", isDocumentValid)
    }
}