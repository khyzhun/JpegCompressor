package com.khyzhun.jpegcompressor.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.repository.review.ReviewRepository
import com.khyzhun.jpegcompressor.extensions.bytes
import com.khyzhun.jpegcompressor.presentation.base.BaseViewModel

class ReviewViewModel(
    private val repository: ReviewRepository
) : BaseViewModel() {

    val selectedImage: LiveData<ByteArray?> =
        repository.selectedImage.asLiveData()

    val selectedImageSize: LiveData<Int> =
        repository.selectedImage
            .asLiveData()
            .map { it.bytes() }

    val compressedImage: LiveData<ByteArray?> =
        repository.compressedImage.asLiveData()

    val compressedImageSize: LiveData<Int> =
        repository.compressedImage
            .asLiveData()
            .map { it.bytes() }

}