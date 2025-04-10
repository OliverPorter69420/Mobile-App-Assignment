package com.example.dissertation_app.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dissertation_app.data.dataset.LibraryBooks
import com.example.dissertation_app.data.dataset.LocalLibraryRepository
import kotlinx.coroutines.flow.Flow

class LibraryBookViewModel(
    private val libraryRepository: LocalLibraryRepository
){
    private val _libraryBooks = MutableLiveData<List<LibraryBooks>>()
    val libraryBooks: LiveData<List<LibraryBooks>> = _libraryBooks

    private val _libraryBook =  MutableLiveData<LibraryBooks?>()
    val libraryBook: LiveData<LibraryBooks?> = _libraryBook

    fun getLibraryBooks(): LiveData<List<LibraryBooks>> {
        return libraryBooks
    }

    fun getLibraryBook(): LiveData<LibraryBooks?> {
        return libraryBook
    }

    suspend fun loadLibraryBooks() {
        val books = libraryRepository.getLibraryBooksStream()
        _libraryBooks.postValue(books)
    }

    suspend fun searchLibraryBook(id: Int) {
        val book = libraryRepository.getLibraryBookStream(id)
        _libraryBook.postValue(book)
    }

    suspend fun addLibraryBook(libraryBook: LibraryBooks) {
        libraryRepository.insertLibraryBook(libraryBook)
    }

    suspend fun deleteLibraryBook(libraryBook: LibraryBooks) {
        libraryRepository.deleteLibraryBook(libraryBook)
    }

    suspend fun updateLibraryBook(libraryBook: LibraryBooks) {
        libraryRepository.updateLibraryBook(libraryBook)
    }
}