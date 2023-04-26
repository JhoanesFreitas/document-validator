package com.jhoanesfreitas.documentvalidator.validators.cpf

import com.jhoanesfreitas.documentvalidator.BuildConfig.FAKE_CPF
import com.jhoanesfreitas.documentvalidator.BuildConfig.FAKE_INVALID_CPF
import com.jhoanesfreitas.documentvalidator.validators.factories.CpfValidatorFactory
import com.jhoanesfreitas.documentvalidator.validators.factories.DocumentValidatorFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CpfValidatorTest {

    private lateinit var documentValidatorFactory: DocumentValidatorFactory

    @Before
    fun setUp() {
        documentValidatorFactory = CpfValidatorFactory()
    }

    @Test
    fun cpfValidator_validateCpf_verifyIfCpfIsValid() {
        val cpfValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cpfValidator.validate(FAKE_CPF)

        Assert.assertTrue("O Cpf é inválido!", isDocumentValid)
    }

    @Test
    fun cpfValidator_validateCpf_verifyIfCpfIsInvalid() {
        val cpfValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cpfValidator.validate(FAKE_INVALID_CPF)

        Assert.assertFalse("O Cpf é válido!", isDocumentValid)
    }
}