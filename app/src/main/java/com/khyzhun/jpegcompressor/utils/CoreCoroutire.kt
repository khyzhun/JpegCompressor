package com.khyzhun.jpegcompressor.utils

import android.util.Log
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * The interface for the core coroutine.
 * It provides the parent job, the coroutine scope, and the launch IO function.
 * @see Job
 * @see CoroutineScope
 * @see CoroutineExceptionHandler
 */
interface CoreCoroutine {

    val parentJob: Job

    val coreCoroutineScope: CoroutineScope

    fun <T> launchIO(call: suspend () -> T)

    fun clearCoroutines()
}

open class CoreCoroutineDelegate(
    override val parentJob: CompletableJob = SupervisorJob(),
    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e("CoreCoroutine", "Unexpected error occurred", throwable)
        },
    override val coreCoroutineScope: CoroutineScope = CoroutineScope(
        Dispatchers.Main + parentJob + exceptionHandler
    ),
) : CoreCoroutine {

    override fun <T> launchIO(call: suspend () -> T) {
        coreCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                call.invoke()
            }
        }
    }


    override fun clearCoroutines() {
        parentJob.cancelChildren()
    }

}