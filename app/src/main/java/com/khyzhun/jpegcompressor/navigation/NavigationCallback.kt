package com.khyzhun.jpegcompressor.navigation

/**
 * Interface for navigation callback. This is used to navigate between destinations.
 * @see [com.khyzhun.jpegcompressor.navigation.Navigation]
 * @see [com.khyzhun.jpegcompressor.navigation.Destination]
 */
interface NavigationCallback {
    fun navigateTo(destination: Destination)
}