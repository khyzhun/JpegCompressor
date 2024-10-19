package com.khyzhun.jpegcompressor.data.repository.review

import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import com.khyzhun.jpegcompressor.domain.repository.review.ReviewRepository
import kotlinx.coroutines.flow.Flow

class ReviewRepositoryImpl(
    private val localDataSource: LocalDataSource
) : ReviewRepository {

    override val selectedImage: Flow<ByteArray?> get() = localDataSource.selectedImage
    override val compressedImage: Flow<ByteArray?> get() = localDataSource.compressedImage

    override suspend fun retrieveSelectedImage(): ByteArray? {
        return localDataSource.retrieveSelectedImage()
    }

    override suspend fun retrieveCompressedImage(): ByteArray? {
        return localDataSource.retrieveCompressedImage()
    }
}