package com.jhoanesfreitas.documentvalidator.validators.rg

import com.jhoanesfreitas.documentvalidator.validators.ValidateException
import com.jhoanesfreitas.documentvalidator.validators.Validator
import com.jhoanesfreitas.documentvalidator.validators.utils.removeSymbols

private const val IS_IT_VALID = true
private const val IS_IT_INVALID = false

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
            checkDigitalChecker()
            IS_IT_VALID
        } catch (e: ValidateException) {
            IS_IT_INVALID
        }
    }

    private fun checkDigitalChecker() {
        val digitalChecker = getDigitalChecker()
        if (rgWithoutMask.last().toString() != digitalChecker)
            throw ValidateException("O dígito verificador é inválido!")
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