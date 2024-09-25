package com.example.assesmenttask.di

import com.example.assesmenttask.data.remote.ApiRepository
import com.example.assesmenttask.network.ApiProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object AppModule {

    private val viewModelModules = module {
    }

    private val repoModules = module {
        single<ApiRepository> { ApiRepository(get()) }
    }

    private val commonModules = module {
        single { ApiProvider.client }
    }

    fun appModules() = viewModelModules + repoModules + commonModules
}