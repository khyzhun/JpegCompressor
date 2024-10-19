package com.khyzhun.jpegcompressor.navigation

/**
 * Sealed class that represents the destinations in the app.
 * Each destination is an object inside the sealed class.
 * This allows to use the class as a type and restrict the possible values to the ones defined here.
 * This is used in the navigation graph to define the navigation paths.
 * @see [com.khyzhun.jpegcompressor.navigation.Navigation]
 * @see [com.khyzhun.jpegcompressor.navigation.NavigationCallback]
 */
sealed interface Destination {
    /**
     * The object that represents the destination of the home screen.
     */
    data object Choose : Destination
    /**
     * The object that represents the destination of the edit screen.
     */
    data object Edit : Destination
    /**
     * The object that represents the destination of the preview screen.
     */
    data object Preview : Destination
}