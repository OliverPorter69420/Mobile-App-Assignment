package com.example.dissertation_app.ui.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dissertation_app.BookApplication
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.library.LibraryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface LibraryUiState {
    data class Success(val libraries: List<Libraries>?) : LibraryUiState
    object Error : LibraryUiState
    object Loading : LibraryUiState
}

class LibraryViewModel(
    val libraryRepository: LibraryRepository
) : ViewModel() {
    var libraryUiState : LibraryUiState by mutableStateOf(LibraryUiState.Loading)
        private set

    companion object {
        val factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val libraryRepository = application.container.libraryRepository
                LibraryViewModel(libraryRepository = libraryRepository)
            }
        }
    }

    fun getLibraries() {
        viewModelScope.launch(Dispatchers.IO) {
            libraryUiState = try {
                val libraries = libraryRepository.getLibraries()
                LibraryUiState.Success(libraries = libraries)
            } catch (e: Exception) {
                LibraryUiState.Error
            }
        }
    }

    fun addLibrary(library: Libraries) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                libraryRepository.insertLibrary(library)
            } catch (e: Exception) {
                LibraryUiState.Error
            }
        }
    }

    fun removeLibrary(libraryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                libraryRepository.deleteLibrary(libraryId)
            } catch (e: Exception) {
                LibraryUiState.Error
            }
        }
    }

    fun updateLibrary(library: Libraries) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                libraryRepository.updateLibrary(library)
            } catch (e: Exception) {
                LibraryUiState.Error
            }
        }
    }
}