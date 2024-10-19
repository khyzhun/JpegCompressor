package com.khyzhun.jpegcompressor.data.repository.edit

import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import com.khyzhun.jpegcompressor.domain.repository.edit.EditRepository
import kotlinx.coroutines.flow.Flow

class EditRepositoryImpl(
    private val localDataSource: LocalDataSource
) : EditRepository {
    override val selectedImage: Flow<ByteArray?> get() = localDataSource.selectedImage
    override val compressedImage: Flow<ByteArray?> get() = localDataSource.compressedImage

    override suspend fun retrieveSelectedImage(): ByteArray? {
        return localDataSource.retrieveSelectedImage()
    }

    override suspend fun saveCompressedImage(image: ByteArray) {
        localDataSource.saveCompressedImage(image)
    }
}