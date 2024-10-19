package com.khyzhun.jpegcompressor.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khyzhun.jpegcompressor.navigation.NavigationCallback

abstract class BaseFragment(private val layout: Int) : Fragment() {

    /**
     * Navigation callback for fragment navigation.
     * It's used to navigate between fragments.
     * @see NavigationCallback
     */
    open var navigationCallback: NavigationCallback? = null

    /**
     * Subscribe for LiveData. It's used to observe LiveData objects.
     * @see Fragment.onViewCreated
     */
    abstract fun subscribeForLiveData()

    override fun onAttach(context: Context) {
        if (context is NavigationCallback) {
            navigationCallback = context
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeForLiveData()
    }

    override fun onDetach() {
        navigationCallback = null
        super.onDetach()
    }
}