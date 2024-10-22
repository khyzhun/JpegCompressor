package com.khyzhun.jpegcompressor.presentation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.entity.CompressionResults
import com.khyzhun.jpegcompressor.domain.repository.edit.EditRepository
import com.khyzhun.jpegcompressor.extensions.kilobytes
import com.khyzhun.jpegcompressor.extensions.calculateByteDifference
import com.khyzhun.jpegcompressor.extensions.calculatePercentageDifference
import com.khyzhun.jpegcompressor.extensions.combineWith
import com.khyzhun.jpegcompressor.presentation.base.BaseViewModel

class EditViewModel(
    private val repository: EditRepository
) : BaseViewModel() {

    val selectedImageUri: LiveData<String> = repository.selectedImageUri.asLiveData()

    private val compressedImage: LiveData<ByteArray?> = repository.compressedImage.asLiveData()
    private val compressedImageSize: LiveData<Double> = compressedImage.map { it.kilobytes() }

    private val selectedImageSize: LiveData<Double> =
        repository.selectedImage
            .asLiveData()
            .map { it.kilobytes() }

    val selectedWithCompressedImages = selectedImageUri.combineWith(compressedImage) { uri, compressed ->
        uri to compressed
    }

    val compressionResult: LiveData<CompressionResults> =
        selectedImageSize.combineWith(compressedImageSize) { selected, compressed ->
            CompressionResults(
                difference = calculateByteDifference(selected, compressed),
                percentage = calculatePercentageDifference(compressed, selected),
            )
        }


    fun saveSelectedImage(selected: ByteArray) = launchIO {
        repository.saveSelectedImage(selected)
    }

    fun saveCompressedImage(compressed: ByteArray) = launchIO {
        repository.saveCompressedImage(compressed)
    }

}
