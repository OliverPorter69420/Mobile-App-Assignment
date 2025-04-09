package com.example.dissertation_app.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dissertation_app.data.dataset.LibraryBooks
import com.example.dissertation_app.data.dataset.LibraryBooksDao
import kotlinx.coroutines.flow.Flow

class LibraryBookViewModel(
    private val libraryBooksDao: LibraryBooksDao
){
    private val libraryBooks: MutableLiveData<Flow<List<LibraryBooks>>> = MutableLiveData()
    private val libraryBook: MutableLiveData<Flow<LibraryBooks?>> = MutableLiveData()

    fun getLibraryBooks(): LiveData<Flow<List<LibraryBooks>>> {
        return libraryBooks
    }

    fun getLibraryBook(): LiveData<Flow<LibraryBooks?>> {
        return libraryBook
    }

    suspend fun loadLibraryBooks() {
        val books = libraryBooksDao.getAllLibraryBooks()
        libraryBooks.postValue(books)
    }

    suspend fun searchLibraryBook(id: Int) {
        val book = libraryBooksDao.getLibraryBookById(id)
        libraryBook.postValue(book)
    }

    suspend fun addLibraryBook(libraryBook: LibraryBooks) {
        libraryBooksDao.insertLibraryBook(libraryBook)
    }

    suspend fun deleteLibraryBook(libraryBook: LibraryBooks) {
        libraryBooksDao.deleteLibraryBook(libraryBook)
    }
}