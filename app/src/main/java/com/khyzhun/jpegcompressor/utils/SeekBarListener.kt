package com.khyzhun.jpegcompressor.utils

import android.widget.SeekBar

/**
 * A simple implementation of [SeekBar.OnSeekBarChangeListener] that allows to override only
 * the [onProgressChanged] method.
 */
interface SeekBarListener : SeekBar.OnSeekBarChangeListener {

    override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

    override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit

}