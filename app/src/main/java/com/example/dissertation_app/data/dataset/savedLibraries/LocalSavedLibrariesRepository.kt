package com.example.dissertation_app.data.dataset.savedLibraries

import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks

class LocalSavedLibrariesRepository(private val savedLibrariesDao: SavedLibrariesDao) : SavedLibrariesRepository {
    override fun getBooksLibraries(bookId: Int): List<Libraries> = savedLibrariesDao.getBooksLibrary(bookId = bookId)

    override fun getBookInSavedLibrary(libraryId: Int): List<LibraryBooks> = savedLibrariesDao.getBooksInLibrary(libraryId)

    override suspend fun saveBookInLibrary(savedLibrary: SavedLibraries) = savedLibrariesDao.saveBookInLibrary(savedLibrary)

    override suspend fun removeBookFromLibrary(libraryId: Int, bookID: Int) = savedLibrariesDao.removeBookFromLibrary(libraryId, bookID)
}