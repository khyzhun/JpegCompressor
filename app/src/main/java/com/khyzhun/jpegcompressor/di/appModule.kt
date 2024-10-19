package com.khyzhun.jpegcompressor.di

import com.khyzhun.jpegcompressor.data.datasource.LocalDataSourceImpl
import com.khyzhun.jpegcompressor.data.repository.choose.ChooseRepositoryImpl
import com.khyzhun.jpegcompressor.data.repository.edit.EditRepositoryImpl
import com.khyzhun.jpegcompressor.data.repository.review.ReviewRepositoryImpl
import com.khyzhun.jpegcompressor.domain.datasource.LocalDataSource
import com.khyzhun.jpegcompressor.domain.repository.choose.ChooseRepository
import com.khyzhun.jpegcompressor.domain.repository.edit.EditRepository
import com.khyzhun.jpegcompressor.domain.repository.review.ReviewRepository
import com.khyzhun.jpegcompressor.presentation.choose.ChooseViewModel
import com.khyzhun.jpegcompressor.presentation.edit.EditViewModel
import com.khyzhun.jpegcompressor.presentation.review.ReviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    /**
     * Data Sources
     */
    single { LocalDataSourceImpl() } bind LocalDataSource::class

    /**
     * Repositories
     */
    single { ChooseRepositoryImpl(get()) } bind ChooseRepository::class
    single { EditRepositoryImpl(get()) } bind EditRepository::class
    single { ReviewRepositoryImpl(get()) } bind ReviewRepository::class

    /**
     * ViewModels
     */

    viewModel { ChooseViewModel(get()) }
    viewModel { EditViewModel(get()) }
    viewModel { ReviewViewModel(get()) }
}
