package com.example.dissertation_app.data.dataset.savedLibraries

interface SavedLibrariesRepository {
    fun getSavedLibraries(libraryId: Int): List<SavedLibraries>

    suspend fun saveBookInLibrary(savedLibrary: SavedLibraries)

    suspend fun removeBookFromLibrary(savedLibrary: SavedLibraries)
}