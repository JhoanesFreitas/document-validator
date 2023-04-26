package com.jhoanesfreitas.documentvalidator.validators.cpf

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

        val isDocumentValid = cpfValidator.validate("554.582.060-45")

        Assert.assertTrue("O Cpf é inválido!", isDocumentValid)
    }

    @Test
    fun cpfValidator_validateCpf_verifyIfCpfIsInvalid() {
        val cpfValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cpfValidator.validate("000.111.222-45")

        Assert.assertFalse("O Cpf é válido!", isDocumentValid)
    }
}