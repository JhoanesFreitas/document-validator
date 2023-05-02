package com.jhoanesfreitas.documentvalidator.validators.cpf

import com.jhoanesfreitas.documentvalidator.BuildConfig.*
import com.jhoanesfreitas.documentvalidator.factories.cpf.CpfValidatorFactory
import com.jhoanesfreitas.documentvalidator.factories.DocumentValidatorFactory
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

    @Test
    fun cpfValidator_validateCpf_verifyIfCpfIsInvalidAfterPassInvalidNumber() {
        val cpfValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cpfValidator.validate("1as.dio.plk-nh")

        Assert.assertFalse("O Cpf é válido mesmo passando uma string com letras!", isDocumentValid)
    }

    @Test
    fun cpfValidator_validateCpf_verifyIfCpfIsInvalidAfterPassEmptyString() {
        val cpfValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cpfValidator.validate("")

        Assert.assertFalse("O Cpf é válido mesmo passando uma string vazia!", isDocumentValid)
    }

    @Test
    fun cpfValidator_validateCpf_verifyIfCpfIsInvalidAfterPassIncompleteNumber() {
        val cpfValidator = documentValidatorFactory.getDocumentValidator()

        val isDocumentValid = cpfValidator.validate(FAKE_INCOMPLETE_CPF)

        Assert.assertFalse("O Cpf é válido mesmo passando um CPF incompleto!", isDocumentValid)
    }
}