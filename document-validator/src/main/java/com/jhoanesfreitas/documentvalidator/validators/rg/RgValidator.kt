package com.jhoanesfreitas.documentvalidator.validators.rg

import com.jhoanesfreitas.documentvalidator.exceptions.DocumentNumberSizeException
import com.jhoanesfreitas.documentvalidator.exceptions.InvalidDocumentException
import com.jhoanesfreitas.documentvalidator.exceptions.NoDecimalDigitException
import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.utils.checkNumberSize
import com.jhoanesfreitas.documentvalidator.validators.utils.removeSymbols

private const val RG_NUMBER_SIZE = 9

private const val RG_IS_VALID = true
private const val RG_IS_INVALID = false

internal class RgValidator internal constructor() : Validator {

    private var originalDocument = ""
    private var rgWithoutMask = ""

    private var digitalCheckerSum = 0

    override fun validate(document: CharSequence): Boolean {
        return validate(document.toString())
    }

    override fun validate(document: String): Boolean {
        originalDocument = document
        return validate()
    }

    private fun validate(): Boolean {
        removeRgMask()
        return isRgValid()
    }

    private fun removeRgMask() {
        rgWithoutMask = originalDocument.removeSymbols()
    }

    private fun isRgValid(): Boolean {
        return try {
            checkNumberSize()
            checkDigitalChecker()
            RG_IS_VALID
        } catch (e: DocumentNumberSizeException) {
            RG_IS_INVALID
        } catch (e: InvalidDocumentException) {
            RG_IS_INVALID
        } catch (e: NoDecimalDigitException) {
            RG_IS_INVALID
        }
    }

    private fun checkNumberSize() {
        rgWithoutMask.length.checkNumberSize(RG_NUMBER_SIZE)
    }

    private fun checkDigitalChecker() {
        val digitalChecker = getDigitalChecker()
        if (rgWithoutMask.last().toString() != digitalChecker)
            throw InvalidDocumentException("O dígito verificador é inválido!")
    }

    private fun getDigitalChecker(): String {
        sumDigitalCheckerSequence()
        return divideDigitalCheckToGetRealDigitalChecker(digitalCheckerSum)
    }

    private fun sumDigitalCheckerSequence() {
        var index = 2

        repeat((0..7).count()) {
            digitalCheckerSum += (rgWithoutMask[it].digitToInt() * index++)
        }
    }

    private fun divideDigitalCheckToGetRealDigitalChecker(sumSequence: Int): String =
        when (sumSequence % 11) {
            1 -> "X"
            0 -> 0.toString()
            else -> (11 - (sumSequence % 11)).toString()
        }
}