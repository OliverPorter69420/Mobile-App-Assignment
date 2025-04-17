package com.example.dissertation_app.ui.items

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
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibraries
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibrariesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface SavedLibraryUiState {
    data class Success(val libraryBooks: List<LibraryBooks>?) : SavedLibraryUiState
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

    fun getLibraryBooks(libraryId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            savedLibraryUiState = try {
                val libraryBooks = savedLibrariesRepository.getBookInSavedLibrary(libraryId)
                SavedLibraryUiState.Success(libraryBooks = libraryBooks)
            } catch (e: Exception) {
                SavedLibraryUiState.Error
            }
        }
    }

    fun saveBookInLibrary(savedLibrary: SavedLibraries) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                savedLibrariesRepository.saveBookInLibrary(savedLibrary)
            } catch (e: Exception) {
                SavedLibraryUiState.Error
            }
        }
    }

    fun removeBookFromLibrary(libraryId: Int, bookId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                savedLibrariesRepository.removeBookFromLibrary(libraryId, bookId)
            } catch (e: Exception) {
                SavedLibraryUiState.Error
            }
        }
    }
}