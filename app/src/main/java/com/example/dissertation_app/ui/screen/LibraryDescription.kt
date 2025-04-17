package com.example.dissertation_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.RotateLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.LibraryBookMenu
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.LibraryViewModel
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryDescriptionLocation : NavigationDestination {
    override val route = "libraryDescriptionScreen"
    override val titleRes = R.string.library_description_screen

    private var savedLibraryViewModel: SavedLibraryViewModel? = null
    private var selectLibraryIndex: Int by mutableIntStateOf(-1)

    fun selectLibraryIndex(id:Int) {
        selectLibraryIndex = id
    }

    fun viewCurrentLibraryIndex(): Int {
        return selectLibraryIndex
    }

    fun getSavedLibraryViewModel(): SavedLibraryViewModel? {
        return savedLibraryViewModel
    }

    fun savedLibraryViewModel(viewModel: SavedLibraryViewModel?) {
        savedLibraryViewModel = viewModel
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryDescription (
    navigateToLibraryScreen: () -> Unit,
    navigateToBookDescription: () -> Unit,
){
    val savedLibraryViewModel: SavedLibraryViewModel? = LibraryDescriptionLocation.getSavedLibraryViewModel()
    val currentIndex = LibraryDescriptionLocation.viewCurrentLibraryIndex()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val icon = Icons.AutoMirrored.Filled.RotateLeft

    savedLibraryViewModel?.getLibraryBooks(
        libraryId = currentIndex
    )!!

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookTopAppBar(
                title = "Library Description Screen",
                canNavigateBack = true,
                navigateUp = navigateToLibraryScreen,
                scrollBehavior = scrollBehavior,
                iconButtonFunctional = true,
                buttonFunctionality = {},
                icon = icon,
                iconDescription = "account"
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LibraryBookMenu(
                uiState = savedLibraryViewModel.savedLibraryUiState,
                navigateToBookDescription = navigateToBookDescription,
                getBooks = savedLibraryViewModel::getLibraryBooks,
                deleteBook = savedLibraryViewModel::removeBookFromLibrary
            )
        }
    }
}