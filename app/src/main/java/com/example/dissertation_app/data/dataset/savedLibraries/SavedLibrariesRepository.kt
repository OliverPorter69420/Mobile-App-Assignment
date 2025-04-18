package com.example.dissertation_app.data.dataset.savedLibraries

import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks

interface SavedLibrariesRepository {

    fun getBooksLibraries(bookId: Int) : List<Libraries>

    fun getBookInSavedLibrary(libraryId: Int): List<LibraryBooks>

    suspend fun saveBookInLibrary(savedLibrary: SavedLibraries)

    suspend fun removeBookFromLibrary(savedLibrary: SavedLibraries)
}