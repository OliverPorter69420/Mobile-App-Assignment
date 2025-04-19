package com.example.dissertation_app.ui.items

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dissertation_app.BookApplication
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibraries
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibrariesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface SavedLibraryUiState {
    data class Success(val libraryBooks: List<LibraryBooks>?, val libraries: List<Libraries>?) : SavedLibraryUiState
    object Error : SavedLibraryUiState
    object Empty : SavedLibraryUiState
}

class SavedLibraryViewModel(
    private val savedLibrariesRepository: SavedLibrariesRepository
) : ViewModel() {

    var savedLibraryUiState: SavedLibraryUiState by mutableStateOf(SavedLibraryUiState.Empty)
        private set

    companion object  {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val savedLibrariesRepository = application.container.savedLibrariesRepository
                SavedLibraryViewModel(savedLibrariesRepository = savedLibrariesRepository)
            }
        }
    }

    fun getBooksLibraries(bookId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            savedLibraryUiState = try {
                val libraries = savedLibrariesRepository.getBooksLibraries(bookId)
                SavedLibraryUiState.Success(libraryBooks = null, libraries = libraries )
            } catch (e: Exception) {
                SavedLibraryUiState.Error
            }
        }
    }

    fun getLibraryBooks(libraryId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            savedLibraryUiState = try {
                val libraryBooks = savedLibrariesRepository.getBookInSavedLibrary(libraryId)
                SavedLibraryUiState.Success(libraryBooks = libraryBooks, libraries = null)
            } catch (e: Exception) {
                SavedLibraryUiState.Error
            }
        }
    }

    fun saveBookInLibrary(savedLibrary: SavedLibraries) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.e("BookMarkAlert", savedLibrary.toString())
                savedLibrariesRepository.saveBookInLibrary(savedLibrary)

            } catch (e: Exception) {

                Log.e("BookMarkAlert", savedLibrary.toString())
                Log.e("BookMarkAlert", e.toString())

                SavedLibraryUiState.Error
            }
        }
    }

    fun removeBookFromLibrary(savedLibrary: SavedLibraries)  {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.e("BookMarkAlert", savedLibrary.toString())

                savedLibrariesRepository.removeBookFromLibrary(savedLibrary)
            } catch (e: Exception) {

                Log.e("BookMarkAlert", savedLibrary.toString())

                Log.e("BookMarkAlert", e.toString())

                SavedLibraryUiState.Error
            }
        }
    }
}