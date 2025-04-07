package com.example.dissertation_app.data.api

import com.example.dissertation_app.network.BookApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val bookRepository: BookRepository
}

class DefaultAppContainer : AppContainer {

    private var baseUrl =
        "https://www.googleapis.com/books/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(RateLimitInterceptor())
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private var retrofitService: BookApiService = retrofit.create(BookApiService::class.java)

    override val bookRepository: BookRepository by lazy {
        NetworkBookRepository(retrofitService)
    }
}