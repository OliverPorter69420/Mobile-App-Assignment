package com.example.dissertation_app.data.dataset

import kotlinx.coroutines.flow.Flow

class LocalLibraryRepository(private val libraryBooksDao: LibraryBooksDao) : LibraryBooksRepository {
    override fun getLibraryBooksStream(): Flow<List<LibraryBooks>> = libraryBooksDao.getAllLibraryBooks()

    override fun getLibraryBookStream(id: Int): Flow<LibraryBooks?> = libraryBooksDao.getLibraryBookById(id)

    override suspend fun insertLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.insertLibraryBook(libraryBook)

    override suspend fun deleteLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.deleteLibraryBook(libraryBook)

    override suspend fun updateLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.updateLibraryBook(libraryBook)
}