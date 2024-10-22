package com.khyzhun.jpegcompressor.presentation.edit

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.core.net.toUri
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.databinding.ActivityEditBinding
import com.khyzhun.jpegcompressor.domain.entity.CompressionResults
import com.khyzhun.jpegcompressor.extensions.toBitmap
import com.khyzhun.jpegcompressor.extensions.doAfterDelay
import com.khyzhun.jpegcompressor.extensions.onClick
import com.khyzhun.jpegcompressor.presentation.base.BaseActivity
import com.khyzhun.jpegcompressor.presentation.review.ReviewActivity
import com.khyzhun.jpegcompressor.utils.BitmapCompressor
import com.khyzhun.jpegcompressor.utils.SeekBarListener
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors

class EditActivity : BaseActivity<ActivityEditBinding>(), SeekBarListener {

    override fun inflateBinding(inflater: LayoutInflater) =
        ActivityEditBinding.inflate(inflater)

    private val viewModel: EditViewModel by viewModel()
    private val glide: RequestManager by inject()

    private var tempBitmap: Bitmap? = null

    private val bitmapCompressor by lazy {
        BitmapCompressor(this)
    }

    private val compressionDispatcher = Executors.newFixedThreadPool(Runtime
        .getRuntime()
        .availableProcessors()
    ).asCoroutineDispatcher()


    override fun initiateView() {
        binding.btnNext.onClick { navigateTo<ReviewActivity>() }
        binding.seekBarCompression.setOnSeekBarChangeListener(this)
    }

    override fun subscribeForLiveData() {
        viewModel.selectedImageUri.observe(::saveUriToTempBitmap)
        viewModel.compressionResult.observe(::renderImageSize)
        viewModel.selectedWithCompressedImages.observe(::renderImage)
    }

    override fun onProgressChanged(
        seekBar: SeekBar?,
        progress: Int,
        fromUser: Boolean
    ) {
        doAfterDelay(compressionDispatcher) {
            bitmapCompressor.compressBitmapByLevel(tempBitmap, progress)?.let {
                viewModel.saveCompressedImage(it)
            }
        }
    }

    private fun saveUriToTempBitmap(uri: String) {
        doAfterDelay(compressionDispatcher, delayMillis = 0) {
            tempBitmap = bitmapCompressor.compressUriToBitmap(uri.toUri())
            bitmapCompressor.compressBitmapByLevel(tempBitmap, 0)?.let {
                viewModel.saveSelectedImage(it)
                viewModel.saveCompressedImage(it)
            }
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
     * Render image in [binding.imageViewCompressing] with Glide.
     * @param pair - Pair of image url and image bytes
     * @see toBitmap
     * @see glide
     * @see binding.imageViewCompressing
     */
    private fun renderImage(pair: Pair<String?, ByteArray?>) {
        glide.load(pair.second.toBitmap())
            .thumbnail(glide.load(pair.first))
            .into(binding.imageViewCompressing)
    }

    override fun onDestroy() {
        tempBitmap = null
        binding.seekBarCompression.setOnSeekBarChangeListener(null)
        compressionDispatcher.close()
        super.onDestroy()
    }

}