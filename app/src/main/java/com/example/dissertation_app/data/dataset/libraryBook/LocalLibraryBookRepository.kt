package com.example.dissertation_app.data.dataset.libraryBook

class LocalLibraryBookRepository(private val libraryBooksDao: LibraryBooksDao) : LibraryBooksRepository {
    override fun getLibraryBooksStream(): List<LibraryBooks> = libraryBooksDao.getAllLibraryBooks()

    override fun getLibraryBookStream(id: Int): LibraryBooks? = libraryBooksDao.getLibraryBookById(id)

    override suspend fun insertLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.insertLibraryBook(libraryBook)

    override suspend fun deleteLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.deleteLibraryBook(libraryBook)

    override suspend fun updateLibraryBook(libraryBook: LibraryBooks) = libraryBooksDao.updateLibraryBook(libraryBook)
}