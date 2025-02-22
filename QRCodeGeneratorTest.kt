package com.example.trainapp.util

import org.junit.Assert.*
import org.junit.Test

class QRCodeGeneratorTest {

    @Test
    fun testQRCodeGeneration() {
        val qrCode = QRCodeGenerator.generateQRCode("Test QR Code", 200, 200)
        assertNotNull(qrCode)
        assertEquals(200, qrCode?.width)
        assertEquals(200, qrCode?.height)
    }
}

