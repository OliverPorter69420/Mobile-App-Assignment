package com.example.dissertation_app.data.dataset


interface LibraryBooksRepository {
    fun getLibraryBooksStream(): List<LibraryBooks>

    fun getLibraryBookStream(id: Int): LibraryBooks?

    suspend fun insertLibraryBook(libraryBook: LibraryBooks)

    suspend fun deleteLibraryBook(libraryBook: LibraryBooks)

    suspend fun updateLibraryBook(libraryBook: LibraryBooks)
}