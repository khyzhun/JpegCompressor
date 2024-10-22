package com.khyzhun.jpegcompressor.presentation.choose

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.khyzhun.jpegcompressor.domain.repository.choose.ChooseRepository
import com.khyzhun.jpegcompressor.presentation.base.BaseViewModel

class ChooseViewModel(
    private val repository: ChooseRepository
) : BaseViewModel() {

    val selectedImageUri: LiveData<String> = repository.selectedImageUri.asLiveData()

    val buttonNextState: LiveData<Boolean> = selectedImageUri.map { it.isNotEmpty() }

    fun saveSelectedImageUri(uriPath: String) {
        launchIO {
            repository.saveSelectedImageUri(uriPath)
        }
    }

}