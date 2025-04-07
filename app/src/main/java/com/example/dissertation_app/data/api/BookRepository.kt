package com.example.dissertation_app.data.api

import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.model.BookResponse
import com.example.dissertation_app.network.BookApiService

interface BookRepository {
    suspend fun getBooks(searchTerm: String): BookResponse

    suspend fun getBookById(bookID: String): BookObjects

    companion object
}

class NetworkBookRepository(
    private val bookApiService: BookApiService
) : BookRepository {
    override suspend fun getBooks(searchTerm: String): BookResponse = bookApiService.getBooks(searchTerm)

    override suspend fun getBookById(bookID: String): BookObjects = bookApiService.getBookById(bookID)
}