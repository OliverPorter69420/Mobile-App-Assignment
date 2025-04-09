package com.example.dissertation_app.data.dataset

import kotlinx.coroutines.flow.Flow

interface LibraryBooksRepository {
    fun getLibraryBooksStream(): Flow<List<LibraryBooks>>

    fun getLibraryBookStream(id: Int): Flow<LibraryBooks?>

    suspend fun insertLibraryBook(libraryBook: LibraryBooks)

    suspend fun deleteLibraryBook(libraryBook: LibraryBooks)

    suspend fun updateLibraryBook(libraryBook: LibraryBooks)
}