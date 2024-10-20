package com.khyzhun.jpegcompressor.extensions

/**
 * Convert byte array to bytes size. If byte array is null, return 0.
 * @return bytes size of byte array
 * @see ByteArray
 */
fun ByteArray?.bytes() = this?.size ?: 0

/**
 * Calculate the difference between two byte sizes.
 * @param selected size of selected image
 * @param compressed size of compressed image
 * @return difference between selected and compressed images
 * @see calculatePercentageDifference
 */
fun calculateByteDifference(selected: Int?, compressed: Int?): Int {
    return (selected ?: 0) - (compressed ?: 0)
}

/**
 * Calculate the percentage difference between two byte sizes.
 * @param compressed size of compressed image
 * @param selected size of selected image
 * @return percentage difference between selected and compressed images
 * @see calculateByteDifference
 */
fun calculatePercentageDifference(compressed: Int?, selected: Int?): Int {
    return 100 - (((compressed?.toFloat() ?: 0f)) / (selected?.toFloat() ?: 0f) * 100).toInt()
}