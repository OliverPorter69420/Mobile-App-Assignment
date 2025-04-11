package com.example.dissertation_app.data

import android.content.Context
import com.example.dissertation_app.data.api.BookRepository
import com.example.dissertation_app.data.api.NetworkBookRepository
import com.example.dissertation_app.data.api.RateLimitInterceptor
import com.example.dissertation_app.data.dataset.LibraryBookDatabase
import com.example.dissertation_app.data.dataset.library.LocalLibraryRepository
import com.example.dissertation_app.data.dataset.libraryBook.LocalLibraryBookRepository
import com.example.dissertation_app.data.dataset.savedLibraries.LocalSavedLibrariesRepository
import com.example.dissertation_app.network.BookApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File

interface AppContainer {
    val bookRepository: BookRepository

    val libraryBookRepository : LocalLibraryBookRepository

    val libraryRepository : LocalLibraryRepository

    val savedLibrariesRepository : LocalSavedLibrariesRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    private var baseUrl =
        "https://www.googleapis.com/books/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(RateLimitInterceptor())
        .cache(
            okhttp3.Cache(
                directory = File(context.cacheDir, "http_cache"),
                maxSize = 50L * 1024L * 1024L
            )
        )
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

    override val libraryBookRepository: LocalLibraryBookRepository by lazy {
        LocalLibraryBookRepository(
            LibraryBookDatabase.getDatabase(context).libraryBooksDao()
        )
    }

    override val libraryRepository: LocalLibraryRepository by lazy {
        LocalLibraryRepository(
            LibraryBookDatabase.getDatabase(context).librariesDao()
        )
    }

    override val savedLibrariesRepository: LocalSavedLibrariesRepository by lazy {
        LocalSavedLibrariesRepository(
            LibraryBookDatabase.getDatabase(context).savedLibrariesDao()
        )
    }
}