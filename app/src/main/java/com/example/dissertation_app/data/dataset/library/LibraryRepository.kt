package com.example.dissertation_app.data.dataset.library

interface LibraryRepository {
    fun getLibrary(id: Int): Libraries?

    fun getLibraries(): List<Libraries>

    suspend fun insertLibrary(library: Libraries)

    suspend fun deleteLibrary(id: Int)

    suspend fun updateLibrary(library: Libraries)
}