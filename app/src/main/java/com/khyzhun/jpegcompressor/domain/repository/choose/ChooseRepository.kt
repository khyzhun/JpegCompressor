package com.khyzhun.jpegcompressor.domain.repository.choose

import kotlinx.coroutines.flow.Flow

interface ChooseRepository {

    val selectedImageUri: Flow<String>

    suspend fun saveSelectedImageUri(uriPath: String)

}