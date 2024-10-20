package com.khyzhun.jpegcompressor.presentation.edit

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.databinding.ActivityEditBinding
import com.khyzhun.jpegcompressor.domain.entity.CompressionResults
import com.khyzhun.jpegcompressor.extensions.onClick
import com.khyzhun.jpegcompressor.presentation.base.BaseActivity
import com.khyzhun.jpegcompressor.presentation.review.ReviewActivity
import com.khyzhun.jpegcompressor.utils.SeekBarListener
import com.khyzhun.jpegcompressor.utils.byteArrayToBitmap
import com.khyzhun.jpegcompressor.utils.compressBitmapByLevel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditActivity : BaseActivity<ActivityEditBinding>(), SeekBarListener {

    override fun inflateBinding(inflater: LayoutInflater) =
        ActivityEditBinding.inflate(inflater)

    private val viewModel: EditViewModel by viewModel()
    private val glide: RequestManager by inject()

    private var tempBitmap: Bitmap? = null

    override fun initiateView() {
        binding.btnNext.onClick { navigateTo<ReviewActivity>() }
        binding.seekBarCompression.setOnSeekBarChangeListener(this)
    }

    override fun subscribeForLiveData() {
        viewModel.selectedImage.observe { tempBitmap = byteArrayToBitmap(it) }
        viewModel.compressedImage.observe(::renderImage)
        viewModel.compressionResult.observe(::renderImageSize)
    }

    override fun onProgressChanged(
        seekBar: SeekBar?,
        progress: Int,
        fromUser: Boolean
    ) = launch {
        compressBitmapByLevel(tempBitmap, progress)?.let {
            viewModel.saveCompressedImage(it)
        }
    }

    /**
     * Render image size in bytes. Show compressed image size in bytes.
     * If image is not compressed, show 0 bytes.
     * @param compression - compressed image
     */
    private fun renderImageSize(compression: CompressionResults) {
        binding.tvImageInfo.text = getString(
            R.string.you_saved_x_which_is_y_lower,
            compression.difference,
            compression.percentage
        )
    }

    /**
     * Render image. Load compressed image to ImageView.
     * If image is not compressed, ImageView will be empty.
     * @param original - compressed image
     * @see byteArrayToBitmap
     * @see glide
     */
    private fun renderImage(original: ByteArray?) {
        glide.load(byteArrayToBitmap(original))
            .thumbnail(glide.load(tempBitmap))
            .into(binding.imageViewCompressing)
    }

}