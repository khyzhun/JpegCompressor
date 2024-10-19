package com.khyzhun.jpegcompressor.domain.repository.review

import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    val selectedImage: Flow<ByteArray?>

    val compressedImage: Flow<ByteArray?>

    suspend fun retrieveSelectedImage(): ByteArray?

    suspend fun retrieveCompressedImage(): ByteArray?

}