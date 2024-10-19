package com.khyzhun.jpegcompressor.extensions

import android.os.SystemClock
import android.view.View

/**
 * A typealias for a lambda that takes a [View] as a parameter and returns [Unit].
 */
typealias ViewCallBack = (View) -> Unit

/**
 * A function that sets a safe click listener on a [View].
 * @param defaultInterval The interval between clicks in milliseconds.
 * @param onSafeClick The lambda that will be called when the [View] is clicked.
 * @see View
 * @see View.OnClickListener
 * @see SystemClock
 */
fun View.setSafeOnClickListener(
    defaultInterval: Int = 500,
    onSafeClick: ViewCallBack,
) {
    val safeClickListener = UISafeClickListener(defaultInterval) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

/**
 * A class that implements the [View.OnClickListener] interface.
 * @param defaultInterval The interval between clicks in milliseconds.
 * @param onSafeClick The lambda that will be called when the [View] is clicked.
 * @see View
 * @see View.OnClickListener
 */
private class UISafeClickListener(
    private var defaultInterval: Int,
    private val onSafeCLick: ViewCallBack,
) : View.OnClickListener {
    companion object {
        private var lastTimeClicked: Long = 0
    }

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}