package com.example.dissertation_app.data.dataset.savedLibraries

class LocalSavedLibraryRepository(private val savedLibrariesDao: SavedLibrariesDao) : SavedLibrariesRepository {
    override fun getSavedLibraries(libraryId: Int): List<SavedLibraries> = savedLibrariesDao.getBooksInLibrary(libraryId)

    override suspend fun saveBookInLibrary(savedLibrary: SavedLibraries) = savedLibrariesDao.saveBookInLibrary(savedLibrary)

    override suspend fun removeBookFromLibrary(savedLibrary: SavedLibraries) = savedLibrariesDao.removeBookFromLibrary(savedLibrary)
}