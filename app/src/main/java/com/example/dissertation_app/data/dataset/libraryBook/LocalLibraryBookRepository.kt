package com.example.dissertation_app.data.dataset.libraryBook

class LocalLibraryBookRepository(private val libraryBooksDao: LibraryBooksDao) : LibraryBooksRepository {

    override fun getLibraryBook(id: String): LibraryBooks? = libraryBooksDao.getLibraryBookById(id)

    override suspend fun insertLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.insertLibraryBook(libraryBook)

    override suspend fun deleteLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.deleteLibraryBook(libraryBook)

    override suspend fun updateLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.updateLibraryBook(libraryBook)
}