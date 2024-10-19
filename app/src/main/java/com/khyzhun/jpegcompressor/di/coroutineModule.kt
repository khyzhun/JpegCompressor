package com.khyzhun.jpegcompressor.di

import com.khyzhun.jpegcompressor.utils.CoreCoroutine
import com.khyzhun.jpegcompressor.utils.CoreCoroutineDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coroutineModule = module {
    factoryOf<CoreCoroutine>(::CoreCoroutineDelegate)
    factory<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
}