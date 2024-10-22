package com.khyzhun.jpegcompressor.data.repository.choose

import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import com.khyzhun.jpegcompressor.domain.repository.choose.ChooseRepository
import kotlinx.coroutines.flow.Flow

class ChooseRepositoryImpl(
    private val localDataSource: LocalDataSource
) : ChooseRepository {

    override val selectedImageUri: Flow<String> = localDataSource.selectedImageUri

    override suspend fun saveSelectedImageUri(uriPath: String) {
        localDataSource.saveSelectedImageUri(uriPath)
    }
}