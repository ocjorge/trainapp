package com.example.trainapp.util

import android.content.Context
import android.util.Log
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.io.IOException

class QRCodeScanner(private val context: Context) {

    fun scanQRCode(imageUri: android.net.Uri, onResult: (String?) -> Unit) {
        try {
            val image = InputImage.fromFilePath(context, imageUri)
            val scanner = BarcodeScanning.getClient()
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        onResult(barcodes[0].rawValue)
                    } else {
                        onResult(null)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("QRCodeScanner", "Error scanning QR code", e)
                    onResult(null)
                }
        } catch (e: IOException) {
            Log.e("QRCodeScanner", "Error creating input image", e)
            onResult(null)
        }
    }
}

