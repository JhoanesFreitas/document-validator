package com.jhoanesfreitas.documentvalidator.validators.cpf

import com.jhoanesfreitas.documentvalidator.exceptions.DocumentNumberSizeException
import com.jhoanesfreitas.documentvalidator.exceptions.InvalidDocumentException
import com.jhoanesfreitas.documentvalidator.exceptions.NoDecimalDigitException
import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.utils.removeSymbols

private const val CPF_NUMBER_SIZE = 11
private const val FIRST_CHECKER_POSITION = 9
private const val SECOND_CHECKER_POSITION = 10

private const val VALUE_THAT_SHOULD_CONSIDER_AS_ZERO = 10

private const val CPF_IS_VALID = true
private const val CPF_IS_INVALID = false

internal class CpfValidator internal constructor() : Validator {

    private var originalDocument = ""
    private var cpfWithoutMask = ""
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
        removeCpfMask()
        return isCpfValid()
    }

    private fun removeCpfMask() {
        cpfWithoutMask = originalDocument.removeSymbols()
    }

    private fun isCpfValid(): Boolean {
        return try {
            checkNumberSize()
            checkFirstDigitalChecker()
            checkSecondDigitalChecker()
            CPF_IS_VALID
        } catch (e: InvalidDocumentException) {
            CPF_IS_INVALID
        } catch (e: NoDecimalDigitException) {
            CPF_IS_INVALID
        } catch (e: DocumentNumberSizeException) {
            CPF_IS_INVALID
        }
    }

    private fun checkNumberSize() {
        if (cpfWithoutMask.length != CPF_NUMBER_SIZE) throw DocumentNumberSizeException()
    }

    private fun checkFirstDigitalChecker() {
        val firstDigitalChecker = getFirstDigitalChecker()

        if (cpfWithoutMask[FIRST_CHECKER_POSITION].digitToInt() != firstDigitalChecker)
            throw InvalidDocumentException("O primeiro dígito verificador é inválido!")
    }

    private fun getFirstDigitalChecker(): Int {
        sumFirstDigitalCheckerSequence()
        val result = divideDigitalCheckToGetRealDigitalChecker(firstSequenceDigitalCheckerSum)
        return if (result == VALUE_THAT_SHOULD_CONSIDER_AS_ZERO) 0 else result
    }

    private fun sumFirstDigitalCheckerSequence() {
        var index = 10

        for (number: Char in cpfWithoutMask.toCharArray()) {
            firstSequenceDigitalCheckerSum += (digitToInt(number) * index--)
            if (index < 2) break
        }
    }

    private fun digitToInt(char: Char): Int = try {
        char.digitToInt()
    } catch (e: IllegalArgumentException) {
        throw NoDecimalDigitException()
    }

    private fun checkSecondDigitalChecker() {
        val secondDigitChecker = getSecondDigitalChecker()

        if (cpfWithoutMask[SECOND_CHECKER_POSITION].digitToInt() != secondDigitChecker)
            throw InvalidDocumentException("O segundo dígito verificador é inválido!")
    }

    private fun getSecondDigitalChecker(): Int {
        sumSecondDigitalCheckerSequence()
        val result = divideDigitalCheckToGetRealDigitalChecker(secondSequenceDigitalCheckerSum)
        return if (result == VALUE_THAT_SHOULD_CONSIDER_AS_ZERO) 0 else result
    }

    private fun sumSecondDigitalCheckerSequence() {
        var index = 11

        for (number: Char in cpfWithoutMask.toCharArray()) {
            secondSequenceDigitalCheckerSum += (number.digitToInt() * index--)
            if (index < 2) break
        }
    }

    private fun divideDigitalCheckToGetRealDigitalChecker(digital: Int): Int {
        return (digital * 10) % 11
    }
}