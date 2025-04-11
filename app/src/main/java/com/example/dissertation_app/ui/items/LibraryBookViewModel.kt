package com.example.dissertation_app.ui.items

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dissertation_app.BookApplication
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.libraryBook.LocalLibraryBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface LibraryBookUiState {
    data class Success(val libraryBooks: List<LibraryBooks>, val libraryBook: LibraryBooks?) : LibraryBookUiState
    object Error : LibraryBookUiState
    object Empty : LibraryBookUiState
    object FunctionSuccess : LibraryBookUiState
}

class LibraryBookViewModel(
    private val libraryRepository: LocalLibraryBookRepository
) : ViewModel() {

    var libraryBookUiState: LibraryBookUiState by mutableStateOf(LibraryBookUiState.Empty)
        private set

    private val _libraryBooks = MutableLiveData<List<LibraryBooks>>()
    val libraryBooks: LiveData<List<LibraryBooks>> = _libraryBooks

    private val _libraryBook =  MutableLiveData<LibraryBooks?>()
    val libraryBook: LiveData<LibraryBooks?> = _libraryBook

    companion object  {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val libraryRepository = application.container.libraryBookRepository
                LibraryBookViewModel(libraryRepository = libraryRepository)
            }
        }
    }

    fun getLibraryBooks() {
        libraryBookUiState = try {

            viewModelScope.launch(Dispatchers.IO) {
                loadLibraryBooks()
            }

            LibraryBookUiState.Success(libraryBooks = libraryBooks.value!!, libraryBook = libraryBook.value!!)

        } catch (e: Exception) {

            LibraryBookUiState.Error

        }
    }

    fun getLibraryBook(id: Int) {
        libraryBookUiState = try {
            viewModelScope.launch(Dispatchers.IO) {
                searchLibraryBook(id)
            }

            LibraryBookUiState.Success(libraryBooks = libraryBooks.value!!, libraryBook = libraryBook.value)

        } catch (e: Exception) {
            LibraryBookUiState.Error
        }
    }

    fun loadLibraryBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            libraryBookUiState = try {
                val books = libraryRepository.getLibraryBooksStream()
                _libraryBooks.postValue(books)
                LibraryBookUiState.FunctionSuccess
            } catch (e: Exception) {
                LibraryBookUiState.Error
            }
        }
    }

    fun searchLibraryBook(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryBookUiState = try {
                val book = libraryRepository.getLibraryBookStream(id)
                _libraryBook.postValue(book)
                LibraryBookUiState.FunctionSuccess
            } catch (e: Exception) {
                LibraryBookUiState.Error
            }
        }
    }

    fun addLibraryBook(libraryBook: LibraryBooks) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryBookUiState = try {
                libraryRepository.insertLibraryBook(libraryBook)
                LibraryBookUiState.FunctionSuccess
            } catch (e: Exception) {
                Log.d("LibraryBookViewModel", "addLibraryBook: $e")
                LibraryBookUiState.Error
            }

            Log.d("LibraryBookViewModel", "addLibraryBook: $libraryBook")
            Log.d("LibraryBookViewModel", "addLibraryBook: $libraryBookUiState")
        }
    }

    fun deleteLibraryBook(libraryBook: LibraryBooks) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryBookUiState = try {
                libraryRepository.deleteLibraryBook(libraryBook)
                LibraryBookUiState.FunctionSuccess
            } catch (e: Exception) {
                LibraryBookUiState.Error
            }
        }
    }

    fun updateLibraryBook(libraryBook: LibraryBooks) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryBookUiState = try {
                libraryRepository.updateLibraryBook(libraryBook)
                LibraryBookUiState.FunctionSuccess
            } catch (e: Exception) {
                LibraryBookUiState.Error
            }
        }
    }
}