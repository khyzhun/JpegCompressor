package com.khyzhun.jpegcompressor.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream

/**
 * Save bitmap to byte array by uri. The quality of the image is specified by the [quality] parameter.
 * @param uri The uri of the image to save.
 * @param quality The quality of the image.
 * @return The byte array of the image.
 * @see Bitmap
 * @see BitmapFactory
 * @see ByteArrayOutputStream
 * @see Uri
 */
fun Context.saveBitmapToByteArray(uri: Uri?, quality: Int): ByteArray? {
    uri ?: return null
    val originalBitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
    if (originalBitmap != null) {
        val outputStream = ByteArrayOutputStream()
        originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        return outputStream.toByteArray()
    }
    return null
}