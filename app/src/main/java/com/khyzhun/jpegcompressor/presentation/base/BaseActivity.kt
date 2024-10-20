package com.khyzhun.jpegcompressor.presentation.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    /**
     * ViewBinding instance. Use for access to views.
     * [ViewBinding] is a feature that allows you to more easily write code that interacts with views.
     */
    protected lateinit var binding: VB

    /**
     * Inflate view binding.
     * @param inflater - layout inflater
     * @return inflated view binding
     */
    abstract fun inflateBinding(inflater: LayoutInflater): VB

    /**
     * Initiate view.
     * Use for setup views and listeners.
     */
    abstract fun initiateView()

    /**
     * Subscribe for live data.
     * Use for observe live data.
     */
    abstract fun subscribeForLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        setContentView(binding.root)
        initiateView()
        subscribeForLiveData()
    }

    /**
     * Launch coroutine.
     * Use for launch coroutine in lifecycle scope.
     * @param action - action for launch
     */
    fun launch(action: () -> Unit) {
        lifecycleScope.launch {
            action()
        }
    }

    /**
     * Observe live data.
     * Use for observe live data in activity.
     * @param observer - observer for live data
     */
    inline fun <T> LiveData<T>.observe(crossinline observer: (T) -> Unit) {
        this.observe(this@BaseActivity) { observer(it) }
    }

    /**
     * Use for navigate to another activity.
     * @param T - activity class
     * @see Activity
     */
    inline fun <reified T : Activity> Activity.navigateTo() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

}