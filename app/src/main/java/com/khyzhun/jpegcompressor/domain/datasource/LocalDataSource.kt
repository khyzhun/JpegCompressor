package com.khyzhun.jpegcompressor.domain.datasource

import kotlinx.coroutines.flow.Flow

/**
 * Interface for local data source. This is used to interact with local data source.
 * local data source can be a database, shared preferences, or file system.
 * @see [com.khyzhun.jpegcompressor.data.datasource.LocalDataSourceImpl]
 * @see [com.khyzhun.jpegcompressor.domain.repository.choose.ChooseRepository]
 */
interface LocalDataSource {

    /**
     * Flow of selected image. This will emit the selected image as a byte array.
     * @see [Flow]
     * @see [ByteArray]
     */
    val selectedImage: Flow<ByteArray?>

    /**
     * Flow of compressed image. This will emit the compressed image as a byte array.
     * @see [Flow]
     * @see [ByteArray]
     */
    val compressedImage: Flow<ByteArray?>




    val selectedImageUri: Flow<String>
    suspend fun saveSelectedImageUri(uriPath: String)
    suspend fun retrieveSelectedImageUri(): String


    /**
     * Save the selected image as a byte array.
     * @param image The selected image as a byte array.
     */
    suspend fun saveSelectedImage(image: ByteArray)

    /**
     * Retrieve the selected image as a byte array.
     * @return The selected image as a byte array.
     */
    suspend fun retrieveSelectedImage(): ByteArray?

    /**
     * Save the compressed image as a byte array.
     * @param image The compressed image as a byte array.
     */
    suspend fun saveCompressedImage(image: ByteArray)

    /**
     * Retrieve the compressed image as a byte array.
     * @return The compressed image as a byte array.
     */
    suspend fun retrieveCompressedImage(): ByteArray?

}