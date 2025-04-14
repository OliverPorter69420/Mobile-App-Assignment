package com.example.dissertation_app.data.dataset.library

class LocalLibraryRepository(private val librariesDao: LibrariesDao) : LibraryRepository {
    override fun getLibrary(id: Int): Libraries? = librariesDao.getLibraryById(id)

    override fun getLibraries(): List<Libraries> = librariesDao.getAllLibraries()

    override suspend fun insertLibrary(library: Libraries) = librariesDao.insertLibrary(library)

    override suspend fun deleteLibrary(id: Int) = librariesDao.deleteLibrary(id)

    override suspend fun updateLibrary(library: Libraries) = librariesDao.updateLibrary(library)
}