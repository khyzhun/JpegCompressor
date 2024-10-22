package com.khyzhun.jpegcompressor.data.repository.edit

import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import com.khyzhun.jpegcompressor.domain.repository.edit.EditRepository
import kotlinx.coroutines.flow.Flow

class EditRepositoryImpl(
    private val localDataSource: LocalDataSource
) : EditRepository {

    override val selectedImage: Flow<ByteArray?> = localDataSource.selectedImage
    override val compressedImage: Flow<ByteArray?> = localDataSource.compressedImage
    override val selectedImageUri: Flow<String> = localDataSource.selectedImageUri

    override suspend fun retrieveSelectedImage(): ByteArray? {
        return localDataSource.retrieveSelectedImage()
    }

    override suspend fun saveSelectedImage(image: ByteArray) {
        localDataSource.saveSelectedImage(image)
    }

    override suspend fun saveCompressedImage(image: ByteArray) {
        localDataSource.saveCompressedImage(image)
    }
}