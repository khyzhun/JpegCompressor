package com.khyzhun.jpegcompressor.presentation.choose

import android.view.LayoutInflater
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import com.bumptech.glide.RequestManager
import com.khyzhun.jpegcompressor.databinding.ActivityChooseBinding
import com.khyzhun.jpegcompressor.extensions.onClick
import com.khyzhun.jpegcompressor.presentation.base.BaseActivity
import com.khyzhun.jpegcompressor.presentation.edit.EditActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseActivity : BaseActivity<ActivityChooseBinding>() {

    override fun inflateBinding(inflater: LayoutInflater) = ActivityChooseBinding.inflate(inflater)

    private val viewModel: ChooseViewModel by viewModel()
    private val glide: RequestManager by inject()

    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        uri?.let {
            viewModel.saveSelectedImageUri(it.toString())
        }
    }

    override fun initiateView() {
        binding.btnChoosePhoto.onClick {
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
        binding.btnNext.onClick {
            navigateTo<EditActivity>()
        }
    }

    override fun subscribeForLiveData() {
        viewModel.buttonNextState.observe(::setupButtonState)
        viewModel.selectedImageUri.observe(::setupSelectedImage)
    }

    /**
     * Setup button state. Enable or disable button. If image is selected, button will be enabled.
     * @param isEnabled - button state
     */
    private fun setupButtonState(isEnabled: Boolean) {
        binding.btnNext.isEnabled = isEnabled
    }

    /**
     *
     */
    private fun setupSelectedImage(uriPath: String) {
        glide.load(uriPath).into(binding.imageViewPreview)
    }

}
