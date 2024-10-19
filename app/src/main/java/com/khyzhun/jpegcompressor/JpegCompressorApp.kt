package com.khyzhun.jpegcompressor

import android.app.Application
import com.khyzhun.jpegcompressor.di.appModule
import com.khyzhun.jpegcompressor.di.coroutineModule
import com.khyzhun.jpegcompressor.di.glideModule
import org.koin.core.context.GlobalContext.startKoin

import org.koin.android.ext.koin.androidContext

/**
 * Main application class.
 * Responsible for initializing Koin modules.
 * @see [appModule]
 * @see [glideModule]
 * @see [coroutineModule]
 *
 */
class JpegCompressorApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@JpegCompressorApp)
            modules(listOf(
                appModule,
                glideModule,
                coroutineModule
            ))
        }
    }

}