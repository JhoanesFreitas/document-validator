package com.jhoanesfreitas.documentvalidator.validators.rg

import com.jhoanesfreitas.documentvalidator.BuildConfig.FAKE_INVALID_RG
import com.jhoanesfreitas.documentvalidator.BuildConfig.FAKE_RG
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
}