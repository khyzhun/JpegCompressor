package com.khyzhun.jpegcompressor.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import org.koin.dsl.module

val glideModule = module {
    single { provideGlide(get()) }
}

private fun provideGlide(context: Context): RequestManager {
    return Glide.with(context)
}