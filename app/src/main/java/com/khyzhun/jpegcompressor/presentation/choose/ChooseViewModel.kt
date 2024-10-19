package com.khyzhun.jpegcompressor.presentation.choose

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.repository.choose.ChooseRepository
import com.khyzhun.jpegcompressor.presentation.base.BaseViewModel

class ChooseViewModel(
    private val repository: ChooseRepository
) : BaseViewModel() {

    val selectedImage: LiveData<ByteArray?> =
        repository.selectedImage.asLiveData()

    val buttonNextState: LiveData<Boolean> =
        repository.selectedImage
            .asLiveData()
            .map { it != null && it.isNotEmpty() }


    fun saveSelectedImage(byteArray: ByteArray?) {
        launchIO {
            byteArray?.let {
                repository.saveSelectedImage(it)
                repository.saveCompressedImage(it)
            }
        }
    }

}