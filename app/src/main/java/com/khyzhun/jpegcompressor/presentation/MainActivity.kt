package com.khyzhun.jpegcompressor.presentation

import com.khyzhun.jpegcompressor.R
import com.khyzhun.jpegcompressor.presentation.base.BaseActivity
import com.khyzhun.jpegcompressor.navigation.Destination
import com.khyzhun.jpegcompressor.navigation.NavigationCallback
import com.khyzhun.jpegcompressor.presentation.choose.ChooseFragment
import com.khyzhun.jpegcompressor.presentation.edit.EditFragment
import com.khyzhun.jpegcompressor.presentation.review.ReviewFragment

class MainActivity : BaseActivity(R.layout.activity_main), NavigationCallback {

    override val initiateFragment = ChooseFragment.newInstance()

    override fun navigateTo(destination: Destination) {
        when (destination) {
            Destination.Choose -> replaceFragment(ChooseFragment.newInstance())
            Destination.Edit -> replaceFragment(EditFragment.newInstance())
            Destination.Preview -> replaceFragment(ReviewFragment.newInstance())
        }
    }

}