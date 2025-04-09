package com.example.dissertation_app.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dissertation_app.data.dataset.LibraryBooks
import com.example.dissertation_app.data.dataset.LocalLibraryRepository
import kotlinx.coroutines.flow.Flow

class LibraryBookViewModel(
    private val libraryRepository: LocalLibraryRepository
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
        val books = libraryRepository.getLibraryBooksStream()
        libraryBooks.postValue(books)
    }

    suspend fun searchLibraryBook(id: Int) {
        val book = libraryRepository.getLibraryBookStream(id)
        libraryBook.postValue(book)
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