package com.example.fail.network.di

import com.example.fail.network.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}
