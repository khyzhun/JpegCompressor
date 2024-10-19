package com.khyzhun.jpegcompressor.presentation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.repository.edit.EditRepository
import com.khyzhun.jpegcompressor.extensions.bytes
import com.khyzhun.jpegcompressor.presentation.base.BaseViewModel

class EditViewModel(
    private val repository: EditRepository
) : BaseViewModel() {

    val selectedImage: LiveData<ByteArray?> =
        repository.selectedImage
            .asLiveData()

    val compressedImage: LiveData<ByteArray?> =
        repository.compressedImage
            .asLiveData()

    val compressedImageSize: LiveData<Int> =
        repository.compressedImage
            .asLiveData()
            .map { it.bytes() }


    fun saveCompressedImage(compressed: ByteArray) = launchIO {
        repository.saveCompressedImage(compressed)
    }

}
