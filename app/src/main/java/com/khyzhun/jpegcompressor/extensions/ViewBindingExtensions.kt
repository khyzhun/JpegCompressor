package com.khyzhun.jpegcompressor.extensions

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * A generic function to create a [ViewBinding] instance for a [Fragment].
 */
inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline bind: (View) -> T
) = lazy { bind(requireView()) }

/**
 * A generic function to create a [ViewBinding] instance for an [Activity].
 */
inline fun <T : ViewBinding> Activity.viewBinding(
    crossinline bind: (View) -> T
) = lazy {
    bind(findViewById(android.R.id.content))
}