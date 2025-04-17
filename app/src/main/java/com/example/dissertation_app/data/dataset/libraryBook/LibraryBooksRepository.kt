package com.example.dissertation_app.data.dataset.libraryBook


interface LibraryBooksRepository {

    fun getLibraryBook(id: String): LibraryBooks?

    suspend fun insertLibraryBook(libraryBook: LibraryBooks)

    suspend fun deleteLibraryBook(libraryBook: LibraryBooks)

    suspend fun updateLibraryBook(libraryBook: LibraryBooks)
}