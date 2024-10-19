package com.khyzhun.jpegcompressor.data.repository.choose

import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import com.khyzhun.jpegcompressor.domain.repository.choose.ChooseRepository
import kotlinx.coroutines.flow.Flow

class ChooseRepositoryImpl(
    private val localDataSource: LocalDataSource
) : ChooseRepository {

    override val selectedImage: Flow<ByteArray?> get() = localDataSource.selectedImage

    override suspend fun saveSelectedImage(image: ByteArray) {
        localDataSource.saveSelectedImage(image)
    }

    override suspend fun saveCompressedImage(image: ByteArray) {
        localDataSource.saveCompressedImage(image)
    }
}