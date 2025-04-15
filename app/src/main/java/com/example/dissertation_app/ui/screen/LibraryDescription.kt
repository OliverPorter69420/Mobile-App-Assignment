package com.example.dissertation_app.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryDescriptionLocation : NavigationDestination {
    override val route = "libraryDescriptionScreen"
    override val titleRes = R.string.library_description_screen

    private var libraryBookViewModel: LibraryBookViewModel? = null
    private var savedLibraryViewModel: SavedLibraryViewModel? = null

    fun getLibraryBookViewModel(): LibraryBookViewModel? {
        return libraryBookViewModel
    }

    fun libraryBookViewModel(viewModel: LibraryBookViewModel?) {
        libraryBookViewModel = viewModel
    }

    fun getSavedLibraryViewModel(): SavedLibraryViewModel? {
        return savedLibraryViewModel
    }

    fun savedLibraryViewModel(viewModel: SavedLibraryViewModel?) {
        savedLibraryViewModel = viewModel
    }
}

@Composable
fun LibraryDescription (
    navigateToLibraryScreen: () -> Unit,
    navigateToBookDescription: () -> Unit,
){
    val libraryBookViewModel: LibraryBookViewModel? = LibraryDescriptionLocation.getLibraryBookViewModel()
    val savedLibraryViewModel: SavedLibraryViewModel? = LibraryDescriptionLocation.getSavedLibraryViewModel()

    Text(text = "Library Description")
}