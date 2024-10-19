package com.khyzhun.jpegcompressor.domain.repository.choose

import kotlinx.coroutines.flow.Flow


interface ChooseRepository {

    val selectedImage: Flow<ByteArray?>

    suspend fun saveSelectedImage(image: ByteArray)

    suspend fun saveCompressedImage(image: ByteArray)

}