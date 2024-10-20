package com.khyzhun.jpegcompressor.presentation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.entity.CompressionResults
import com.khyzhun.jpegcompressor.domain.repository.edit.EditRepository
import com.khyzhun.jpegcompressor.extensions.bytes
import com.khyzhun.jpegcompressor.extensions.calculateByteDifference
import com.khyzhun.jpegcompressor.extensions.calculatePercentageDifference
import com.khyzhun.jpegcompressor.extensions.combineWith
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

    private val selectedImageSize: LiveData<Int> =
        repository.selectedImage
            .asLiveData()
            .map { it.bytes() }

    private val compressedImageSize: LiveData<Int> =
        repository.compressedImage
            .asLiveData()
            .map { it.bytes() }


    val compressionResult: LiveData<CompressionResults> =
        selectedImageSize.combineWith(compressedImageSize) { selected, compressed ->
            CompressionResults(
                difference = calculateByteDifference(selected, compressed),
                percentage = calculatePercentageDifference(compressed, selected),
            )
        }


    fun saveCompressedImage(compressed: ByteArray) = launchIO {
        repository.saveCompressedImage(compressed)
    }

}
