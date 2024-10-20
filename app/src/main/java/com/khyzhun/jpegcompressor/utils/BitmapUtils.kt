package com.khyzhun.jpegcompressor.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/**
 * Compresses the image by the specified level.
 */
const val COMPRESSION_QUALITY_100 = 100

/**
 * Compresses the image by the specified level.
 * @param originalImage The original image to compress.
 * @param progress The level of compression.
 * @return The compressed image as a byte array.
 * @see Bitmap
 */
fun compressBitmapByLevel(originalImage: Bitmap?, progress: Int): ByteArray? {
    originalImage ?: return null
    val quality = 100 - progress
    val outputStream = ByteArrayOutputStream()
    originalImage.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
    return outputStream.toByteArray()
}

/**
 * Converts a byte array to a bitmap.
 * @param byteArray The byte array to convert.
 * @return The bitmap.
 * @see Bitmap
 * @see BitmapFactory
 */
fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? {
    return byteArray?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
}