package com.khyzhun.jpegcompressor.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.khyzhun.jpegcompressor.utils.CoreCoroutine
import com.khyzhun.jpegcompressor.utils.CoreCoroutineDelegate
import org.koin.core.component.KoinComponent

open class BaseViewModel(
    coreCoroutine: CoreCoroutine = CoreCoroutineDelegate(),
) : ViewModel(coreCoroutine.coreCoroutineScope),
    LifecycleObserver,
    KoinComponent,
    CoreCoroutine by coreCoroutine {


    @CallSuper
    override fun onCleared() {
        clearCoroutines()
        super.onCleared()
    }
}