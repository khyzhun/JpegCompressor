package com.khyzhun.jpegcompressor.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * Convert byte array to bytes size. If byte array is null, return 0.
 * @return bytes size of byte array
 * @see ByteArray
 */
fun ByteArray?.kilobytes(): Double = if (this != null) this.size / 1024.0 else 0.0

/**
 * Calculate the difference between two byte sizes.
 * @param selected size of selected image
 * @param compressed size of compressed image
 * @return difference between selected and compressed images
 * @see calculatePercentageDifference
 */
fun calculateByteDifference(selected: Double?, compressed: Double?): Double {
    return (selected ?: 0.0) - (compressed ?: 0.0)
}

/**
 * Calculate the percentage difference between two byte sizes.
 * @param compressed size of compressed image
 * @param selected size of selected image
 * @return percentage difference between selected and compressed images
 * @see calculateByteDifference
 */
fun calculatePercentageDifference(compressed: Double?, selected: Double?): Int {
    return 100 - (((compressed ?: 0.0)) / (selected ?: 0.0) * 100).toInt()
}

/**
 * Converts a byte array to a bitmap.
 * @param byteArray The byte array to convert.
 * @return The bitmap.
 * @see Bitmap
 * @see BitmapFactory
 */
fun ByteArray?.toBitmap(): Bitmap? {
    return this?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
}