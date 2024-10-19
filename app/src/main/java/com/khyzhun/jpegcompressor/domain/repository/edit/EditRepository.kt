package com.khyzhun.jpegcompressor.domain.repository.edit

import kotlinx.coroutines.flow.Flow

interface EditRepository {

    val selectedImage: Flow<ByteArray?>

    val compressedImage: Flow<ByteArray?>

    suspend fun retrieveSelectedImage(): ByteArray?

    suspend fun saveCompressedImage(image: ByteArray)

}