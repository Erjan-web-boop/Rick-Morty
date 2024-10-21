package com.example.fail.network.di

import com.example.fail.network.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
}
