package com.khyzhun.jpegcompressor.presentation.review

import android.view.LayoutInflater
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.databinding.ActivityPreviewBinding
import com.khyzhun.jpegcompressor.extensions.toBitmap
import com.khyzhun.jpegcompressor.extensions.kilobytes
import com.khyzhun.jpegcompressor.presentation.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewActivity : BaseActivity<ActivityPreviewBinding>() {

    override fun inflateBinding(inflater: LayoutInflater) =
        ActivityPreviewBinding.inflate(inflater)

    private val viewModel: ReviewViewModel by viewModel()
    private val glide: RequestManager by inject()

    /**
     * Not used in this activity.
     */
    override fun initiateView() = Unit

    /**
     * Subscribe for LiveData. Render selected image size, selected image,
     * compressed image size and compressed image.
     * @see renderSelectedImageSize
     * @see renderSelectedImage
     * @see renderCompressedImageSize
     * @see renderCompressedImage
     */
    override fun subscribeForLiveData() {
        viewModel.selectedImageSize.observe(::renderSelectedImageSize)
        viewModel.selectedImage.observe(::renderSelectedImage)
        viewModel.compressedImageSize.observe(::renderCompressedImageSize)
        viewModel.compressedImage.observe(::renderCompressedImage)
    }

    /**
     * Render selected image size in bytes. Show original image size in bytes.
     * If image is not selected, show 0 bytes.
     * @param size - original image size in bytes
     * @see kilobytes
     * @see binding
     */
    private fun renderSelectedImageSize(size: Double) {
        binding.tvImageBeforeInfo.text = getString(R.string.original_image_size_bytes, size)
    }

    /**
     * Render selected image. Load original image to ImageView.
     * If image is not selected, ImageView will be empty.
     * @param image - original image
     * @see toBitmap
     * @see glide
     */
    private fun renderSelectedImage(image: ByteArray?) {
        glide.load(image.toBitmap()).into(binding.imageViewBefore)
    }

    /**
     * Render compressed image size in bytes. Show compressed image size in bytes.
     * If image is not compressed, show 0 bytes.
     * @param size - compressed image size in bytes
     * @see kilobytes
     * @see binding
     */
    private fun renderCompressedImageSize(size: Double) {
        binding.tvImageAfterInfo.text = getString(R.string.compressed_image_size_bytes, size)
    }

    /**
     * Render compressed image. Load compressed image to ImageView.
     * If image is not compressed, ImageView will be empty.
     * @param image - compressed image
     * @see toBitmap
     * @see glide
     */
    private fun renderCompressedImage(image: ByteArray?) {
        glide.load(image.toBitmap()).into(binding.imageViewAfter)
    }

}
