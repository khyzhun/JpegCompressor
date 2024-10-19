package com.khyzhun.jpegcompressor.presentation.choose

import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.databinding.FragmentChooseBinding
import com.khyzhun.jpegcompressor.extensions.saveBitmapToByteArray
import com.khyzhun.jpegcompressor.extensions.setSafeOnClickListener
import com.khyzhun.jpegcompressor.extensions.viewBinding
import com.khyzhun.jpegcompressor.navigation.Destination
import com.khyzhun.jpegcompressor.presentation.base.BaseFragment
import com.khyzhun.jpegcompressor.utils.COMPRESSION_QUALITY_100
import com.khyzhun.jpegcompressor.utils.byteArrayToBitmap
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseFragment : BaseFragment(R.layout.fragment_choose) {

    private val binding by viewBinding(FragmentChooseBinding::bind)
    private val viewModel: ChooseViewModel by viewModel()
    private val glide: RequestManager by inject()

    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        uri?.let {
            val byteArray = context?.saveBitmapToByteArray(it, COMPRESSION_QUALITY_100)
            viewModel.saveSelectedImage(byteArray)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChoosePhoto.setSafeOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
        binding.btnNext.setSafeOnClickListener {
            navigationCallback?.navigateTo(Destination.Edit)
        }
    }

    override fun subscribeForLiveData() {
        viewModel.buttonNextState.observe(viewLifecycleOwner, ::setupButtonState)
        viewModel.selectedImage.observe(viewLifecycleOwner, ::setupSelectedImage)
    }

    /**
     * Setup button state. Enable or disable button. If image is selected, button will be enabled.
     * @param isEnabled - button state
     */
    private fun setupButtonState(isEnabled: Boolean) {
        binding.btnNext.isEnabled = isEnabled
    }

    /**
     * Setup selected image. Load image to ImageView. If image is not selected, ImageView will be empty.
     * @param image - selected image
     * @see byteArrayToBitmap
     */
    private fun setupSelectedImage(image: ByteArray?) {
        glide.load(byteArrayToBitmap(image)).into(binding.imageViewPreview)
    }

    companion object {
        /**
         * Use this method to create new instance of [ChooseFragment].
         */
        fun newInstance() = ChooseFragment()
    }
}
