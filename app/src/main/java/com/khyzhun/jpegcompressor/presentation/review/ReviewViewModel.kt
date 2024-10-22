package com.khyzhun.jpegcompressor.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.repository.review.ReviewRepository
import com.khyzhun.jpegcompressor.extensions.kilobytes
import com.khyzhun.jpegcompressor.presentation.base.BaseViewModel

class ReviewViewModel(
    private val repository: ReviewRepository
) : BaseViewModel() {

    val selectedImage: LiveData<ByteArray?> = repository.selectedImage.asLiveData()

    val selectedImageSize: LiveData<Double> = selectedImage.map { it.kilobytes() }

    val compressedImage: LiveData<ByteArray?> = repository.compressedImage.asLiveData()

    val compressedImageSize: LiveData<Double> = compressedImage.map { it.kilobytes() }

}