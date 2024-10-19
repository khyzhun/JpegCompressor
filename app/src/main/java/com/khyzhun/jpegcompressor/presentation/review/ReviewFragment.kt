package com.khyzhun.jpegcompressor.presentation.review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.presentation.base.BaseFragment
import com.khyzhun.jpegcompressor.databinding.FragmentPreviewBinding
import com.khyzhun.jpegcompressor.extensions.bytes
import com.khyzhun.jpegcompressor.extensions.viewBinding
import com.khyzhun.jpegcompressor.utils.byteArrayToBitmap
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewFragment : BaseFragment(R.layout.fragment_preview) {

    private val binding: FragmentPreviewBinding by viewBinding(FragmentPreviewBinding::bind)
    private val viewModel: ReviewViewModel by viewModel()
    private val glide: RequestManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeForLiveData()
    }

    /**
     * Subscribe for LiveData. Render selected image size, selected image,
     * compressed image size and compressed image.
     * @see renderSelectedImageSize
     * @see renderSelectedImage
     * @see renderCompressedImageSize
     * @see renderCompressedImage
     */
    override fun subscribeForLiveData() {
        viewModel.selectedImageSize.observe(viewLifecycleOwner, ::renderSelectedImageSize)
        viewModel.selectedImage.observe(viewLifecycleOwner, ::renderSelectedImage)
        viewModel.compressedImageSize.observe(viewLifecycleOwner, ::renderCompressedImageSize)
        viewModel.compressedImage.observe(viewLifecycleOwner, ::renderCompressedImage)
    }

    /**
     * Render selected image size in bytes. Show original image size in bytes.
     * If image is not selected, show 0 bytes.
     * @param size - original image size in bytes
     * @see bytes
     * @see binding
     */
    private fun renderSelectedImageSize(size: Int) {
        binding.tvImageBeforeInfo.text = getString(R.string.original_image_size_bytes, size)
    }

    /**
     * Render selected image. Load original image to ImageView.
     * If image is not selected, ImageView will be empty.
     * @param image - original image
     * @see byteArrayToBitmap
     * @see glide
     */
    private fun renderSelectedImage(image: ByteArray?) {
        glide.load(byteArrayToBitmap(image)).into(binding.imageViewBefore)
    }

    /**
     * Render compressed image size in bytes. Show compressed image size in bytes.
     * If image is not compressed, show 0 bytes.
     * @param size - compressed image size in bytes
     * @see bytes
     * @see binding
     */
    private fun renderCompressedImageSize(size: Int) {
        binding.tvImageAfterInfo.text = getString(R.string.compressed_image_size_bytes, size)
    }

    /**
     * Render compressed image. Load compressed image to ImageView.
     * If image is not compressed, ImageView will be empty.
     * @param image - compressed image
     * @see byteArrayToBitmap
     * @see glide
     */
    private fun renderCompressedImage(image: ByteArray?) {
        glide.load(byteArrayToBitmap(image)).into(binding.imageViewAfter)
    }

    companion object {
        /**
         * Use this method to create new instance of [ReviewFragment].
         */
        fun newInstance() = ReviewFragment()
    }
}
