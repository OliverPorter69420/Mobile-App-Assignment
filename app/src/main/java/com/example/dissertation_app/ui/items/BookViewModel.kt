package com.example.dissertation_app.ui.items

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dissertation_app.data.api.BookRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.dissertation_app.BookApplication
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.model.BookResponse
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookUiState {
    data class Success(val bookSearch: List<BookObjects>?, val thumbnails: MutableList<String>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
    object Empty : BookUiState
    object Start : BookUiState
}

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Start)
        private set

    var searchTerm: String = ""

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            if (searchTerm != "") {

                bookUiState = BookUiState.Loading

                bookUiState = try {
                    val response: BookResponse = bookRepository.getBooks(searchTerm)

                    if (response.items?.isEmpty() == true) {
                        BookUiState.Error
                    } else {

                        var thumbnails = getThumbnails(response.items)

                        BookUiState.Success(response.items, thumbnails)
                    }
                } catch (e: IOException) {
                    BookUiState.Error
                } catch (e: HttpException) {
                    BookUiState.Error
                }
            } else {
                BookUiState.Empty
            }
        }
    }

    fun getBookDetails(
        bookID: String?
    ) {
        viewModelScope.launch {
            val bookObject = bookRepository.getBookById(
                bookID.toString()
            )

            bookUiState = BookUiState.Success(
                listOf(bookObject),
                mutableListOf(bookObject.volumeInfo?.imageLinks?.thumbnail.toString())
            )
        }
    }

    fun updateSearchTerm(newSearchTerm: String) {
        searchTerm = newSearchTerm
    }

    fun resetSearchTerm() {
        bookUiState = BookUiState.Start
    }

    private suspend fun getThumbnails(
        books: List<BookObjects>?
    ): MutableList<String> {
        var thumbnails: MutableList<String> = mutableListOf()

        books?.forEach {
            val respond = bookRepository.getBookById(it.id.toString())

            respond.volumeInfo?.imageLinks?.let {

                if (it.large != null) {
                    thumbnails.add(it.large.toString())
                }

                else if (it.medium != null) {
                    thumbnails.add(it.medium.toString())
                }

                else if (it.small != null) {
                    thumbnails.add(it.small.toString())
                }

                else {
                    thumbnails.add(it.thumbnail.toString())
                }
            }
        }

        return thumbnails
    }
}
