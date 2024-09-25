package com.example.assesmenttask.network

import com.example.assesmenttask.data.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider : KoinComponent {

    private fun loggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun httpClient() =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor())
        }.build()

    private val retrofit = Retrofit.Builder()
        .apply {
        baseUrl("https://api.github.com/")
        addConverterFactory(GsonConverterFactory.create())
        client(httpClient())
    }.build()

    val client : ApiService by lazy { retrofit.create(ApiService::class.java) }
}