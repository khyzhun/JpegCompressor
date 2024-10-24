package com.khyzhun.jpegcompressor.data.datasource

import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocalDataSourceImpl : LocalDataSource {

    private val _selectedImage = MutableStateFlow<ByteArray?>(null)
    override val selectedImage: Flow<ByteArray?> get() = _selectedImage.asStateFlow()

    private val _compressedImage = MutableStateFlow<ByteArray?>(null)
    override val compressedImage: Flow<ByteArray?> get() = _compressedImage.asStateFlow()

    private val _selectedImageUri = MutableStateFlow("")
    override val selectedImageUri: Flow<String> get() = _selectedImageUri.asStateFlow()

    override suspend fun saveSelectedImageUri(uriPath: String) {
        _selectedImageUri.emit(uriPath)
    }

    override suspend fun retrieveSelectedImageUri(): String {
        return _selectedImageUri.value
    }

    override suspend fun saveSelectedImage(image: ByteArray) {
        _selectedImage.emit(image)
    }

    override suspend fun retrieveSelectedImage(): ByteArray? {
        return _selectedImage.value
    }

    override suspend fun saveCompressedImage(image: ByteArray) {
        _compressedImage.emit(image)
    }

    override suspend fun retrieveCompressedImage(): ByteArray? {
        return _compressedImage.value
    }

}