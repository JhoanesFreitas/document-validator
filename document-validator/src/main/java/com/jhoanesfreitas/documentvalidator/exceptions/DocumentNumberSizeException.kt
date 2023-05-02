package com.jhoanesfreitas.documentvalidator.exceptions

class DocumentNumberSizeException(override val message: String = "O tamanho do número do documento é insuficiente.") :
    StringIndexOutOfBoundsException() {
}