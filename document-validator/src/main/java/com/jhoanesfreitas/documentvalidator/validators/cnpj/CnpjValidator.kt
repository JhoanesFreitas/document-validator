package com.jhoanesfreitas.documentvalidator.validators.cnpj

import com.jhoanesfreitas.documentvalidator.exceptions.DocumentNumberSizeException
import com.jhoanesfreitas.documentvalidator.exceptions.InvalidDocumentException
import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.utils.checkNumberSize
import com.jhoanesfreitas.documentvalidator.validators.utils.removeSymbols

private const val CNPJ_NUMBER_SIZE = 14
private const val FIRST_CHECKER_POSITION = 12

private const val CNPJ_IS_VALID = true
private const val CNPJ_IS_INVALID = false

internal class CnpjValidator internal constructor() : Validator {

    private var originalDocument = ""
    private var cnpjWithoutMask = ""
    private var firstSequenceDigitalCheckerSum = 0
    private var secondSequenceDigitalCheckerSum = 0

    override fun validate(document: CharSequence): Boolean {
        return validate(document.toString())
    }

    override fun validate(document: String): Boolean {
        originalDocument = document
        return validate()
    }

    private fun validate(): Boolean {
        removeCnpjMask()
        return isCnpjValid()
    }

    private fun removeCnpjMask() {
        cnpjWithoutMask = originalDocument.removeSymbols()
    }

    private fun isCnpjValid(): Boolean {
        return try {
            checkNumberSize()
            checkFirstDigitalChecker()
            checkSecondDigitalChecker()
            CNPJ_IS_VALID
        } catch (e: InvalidDocumentException) {
            CNPJ_IS_INVALID
        } catch (e: IllegalArgumentException) {
            CNPJ_IS_INVALID
        } catch (e: DocumentNumberSizeException) {
            CNPJ_IS_INVALID
        }
    }

    private fun checkNumberSize() {
        cnpjWithoutMask.length.checkNumberSize(CNPJ_NUMBER_SIZE)
    }

    private fun checkFirstDigitalChecker() {
        val firstDigitalChecker = getFirstDigitalChecker()

        if (cnpjWithoutMask[FIRST_CHECKER_POSITION].digitToInt() != firstDigitalChecker)
            throw InvalidDocumentException("O primeiro dígito verificador é inválido!")
    }

    private fun getFirstDigitalChecker(): Int {
        sumFirstDigitalCheckerSequence()
        return divideDigitalCheckToGetRealDigitalChecker(firstSequenceDigitalCheckerSum)
    }

    private fun sumFirstDigitalCheckerSequence() {
        var index = 5

        repeat((0..11).count()) {
            firstSequenceDigitalCheckerSum += ((cnpjWithoutMask[it]).digitToInt() * index)
            if (index == 2) index = 9 else index--
        }
    }

    private fun checkSecondDigitalChecker() {
        val secondDigitalChecker = getSecondDigitalChecker()

        if (cnpjWithoutMask.last().digitToInt() != secondDigitalChecker)
            throw InvalidDocumentException("O primeiro dígito verificador é inválido!")
    }

    private fun getSecondDigitalChecker(): Int {
        sumSecondDigitalCheckerSequence()
        return divideDigitalCheckToGetRealDigitalChecker(secondSequenceDigitalCheckerSum)
    }

    private fun sumSecondDigitalCheckerSequence() {
        var index = 6
        val firstDigitalChecker = getFirstDigitalChecker()
        repeat((1..13).count()) {
            secondSequenceDigitalCheckerSum += when {
                it < 12 -> ((cnpjWithoutMask[it]).digitToInt() * index)
                else -> (firstDigitalChecker * index)
            }
            if (index == 2) index = 9 else index--
        }
    }

    private fun divideDigitalCheckToGetRealDigitalChecker(digital: Int): Int {
        return if (digital % 11 < 2) 0 else 11 - (digital % 11)
    }
}