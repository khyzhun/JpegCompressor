package com.khyzhun.jpegcompressor.extensions

fun ByteArray?.bytes() = this?.size ?: 0

fun ByteArray?.orEmpty() = this ?: byteArrayOf()