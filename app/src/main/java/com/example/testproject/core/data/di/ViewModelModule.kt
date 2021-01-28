package com.example.testproject.core.data.di

import com.example.testproject.ui.home_page.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val viewModelModule = module {
    viewModel { HomeViewModel(androidApplication()) }
}