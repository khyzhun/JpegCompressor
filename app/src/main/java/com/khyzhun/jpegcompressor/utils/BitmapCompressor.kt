package com.khyzhun.jpegcompressor.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream

class BitmapCompressor(private val context: Context) {

    private val bufferPool = mutableListOf<ByteArray>()

    private fun getByteArrayOutputStream(): ByteArrayOutputStream {
        return ByteArrayOutputStream(BUFFER_SIZE)
    }

    private fun getBuffer(): ByteArray {
        return bufferPool.removeLastOrNull() ?: ByteArray(BUFFER_SIZE)
    }

    private fun returnBuffer(buffer: ByteArray) {
        bufferPool.add(buffer)
    }

    fun compressBitmapByLevel(originalImage: Bitmap?, progress: Int): ByteArray? {
        originalImage ?: return null
        val quality = 100 - progress

        val outputStream = getByteArrayOutputStream()
        val buffer = getBuffer()

        return try {
            originalImage.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.write(buffer)
            outputStream.toByteArray()
        } finally {
            outputStream.reset()
            returnBuffer(buffer)
        }
    }

    fun compressUriToBitmap(uri: Uri, quality: Int = COMPRESSION_QUALITY_100): Bitmap? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val originalBitmap = BitmapFactory.decodeStream(inputStream) ?: return null

            val outputStream = getByteArrayOutputStream()
            val buffer = getBuffer()

            return try {
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                outputStream.write(buffer)
                val compressedImageBytes = outputStream.toByteArray()
                BitmapFactory.decodeByteArray(compressedImageBytes, 0, compressedImageBytes.size)
            } finally {
                outputStream.reset()
                returnBuffer(buffer)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private companion object {
        private const val BUFFER_SIZE = 1024 * 1024
        private const val COMPRESSION_QUALITY_100 = 100
    }
}