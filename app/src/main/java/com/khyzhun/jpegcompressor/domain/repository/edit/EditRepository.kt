package com.khyzhun.jpegcompressor.domain.repository.edit

import kotlinx.coroutines.flow.Flow

interface EditRepository {

    val selectedImage: Flow<ByteArray?>

    val compressedImage: Flow<ByteArray?>

    val selectedImageUri: Flow<String>

    suspend fun retrieveSelectedImage(): ByteArray?

    suspend fun saveSelectedImage(image: ByteArray)

    suspend fun saveCompressedImage(image: ByteArray)

}