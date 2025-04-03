package com.example.dissertation_app.network

import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") searchTerm: String): BookResponse

    @GET("volumes/{bookId}")
    suspend fun getBookById(@Path("bookId") bookId: String): BookObjects
}

