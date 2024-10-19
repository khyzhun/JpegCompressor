package com.khyzhun.jpegcompressor.presentation.base

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.khyzhun.jpegcompressor.R

abstract class BaseActivity(private val layoutResId: Int) : AppCompatActivity() {

    /**
     * The fragment that will be initiated when the activity is created. This should be the
     * starting point of the application. This is an abstract property that must be overridden
     * by the subclass.
     */
    abstract val initiateFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        replaceFragment(initiateFragment)
        onBackPressedDispatcher.addCallback(enabled = false) { /* no-op */ }
    }

    /**
     * This is a utility method that can be used by subclasses to replace the current fragment.
     */
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}