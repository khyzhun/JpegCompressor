package com.khyzhun.jpegcompressor.presentation.edit

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.presentation.base.BaseFragment
import com.khyzhun.jpegcompressor.databinding.FragmentEditBinding
import com.khyzhun.jpegcompressor.extensions.setSafeOnClickListener
import com.khyzhun.jpegcompressor.extensions.viewBinding
import com.khyzhun.jpegcompressor.navigation.Destination
import com.khyzhun.jpegcompressor.utils.SeekBarListener
import com.khyzhun.jpegcompressor.utils.byteArrayToBitmap
import com.khyzhun.jpegcompressor.utils.compressBitmapByLevel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : BaseFragment(R.layout.fragment_edit), SeekBarListener {

    private val binding by viewBinding(FragmentEditBinding::bind)
    private val viewModel: EditViewModel by viewModel()
    private val glide: RequestManager by inject()

    private var tempBitmap: Bitmap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setSafeOnClickListener {
            navigationCallback?.navigateTo(Destination.Preview)
        }
        binding.seekBarCompression.setOnSeekBarChangeListener(this)
    }

    override fun subscribeForLiveData() {
        viewModel.selectedImage.observe(viewLifecycleOwner) { tempBitmap = byteArrayToBitmap(it) }
        viewModel.compressedImage.observe(viewLifecycleOwner, ::renderImage)
        viewModel.compressedImageSize.observe(viewLifecycleOwner, ::renderImageSize)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        launch {
            compressBitmapByLevel(tempBitmap, progress)?.let {
                viewModel.saveCompressedImage(it)
            }
        }
    }

    /**
     * Render image size in bytes. Show compressed image size in bytes.
     * If image is not compressed, show 0 bytes.
     * @param size - compressed image size in bytes
     */
    private fun renderImageSize(size: Int) {
        binding.tvImageInfo.text = getString(R.string.compressed_image_size_bytes, size)
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

    companion object {
        /**
         * Use this method to create new instance of [EditFragment].
         */
        fun newInstance() = EditFragment()
    }
}