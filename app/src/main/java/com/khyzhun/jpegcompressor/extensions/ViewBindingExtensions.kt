package com.khyzhun.jpegcompressor.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * A generic function to create a [ViewBinding] instance for a [Fragment].
 */
inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline bind: (View) -> T
) = lazy { bind(requireView()) }