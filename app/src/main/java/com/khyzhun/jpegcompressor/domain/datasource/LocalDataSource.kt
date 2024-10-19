package com.khyzhun.jpegcompressor.domain.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    val selectedImage: Flow<ByteArray?>
    val compressedImage: Flow<ByteArray?>

    suspend fun saveSelectedImage(image: ByteArray)
    suspend fun retrieveSelectedImage(): ByteArray?

    suspend fun saveCompressedImage(image: ByteArray)
    suspend fun retrieveCompressedImage(): ByteArray?

}