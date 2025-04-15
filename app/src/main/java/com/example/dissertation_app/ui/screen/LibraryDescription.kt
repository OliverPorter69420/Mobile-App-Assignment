package com.example.dissertation_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.dissertation_app.BookTopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryDescription (
    navigateToLibraryScreen: () -> Unit,
    navigateToBookDescription: () -> Unit,
){
    val libraryBookViewModel: LibraryBookViewModel? = LibraryDescriptionLocation.getLibraryBookViewModel()
    val savedLibraryViewModel: SavedLibraryViewModel? = LibraryDescriptionLocation.getSavedLibraryViewModel()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookTopAppBar(
                title = "Main Screen",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                iconButtonFunctional = true,
                buttonFunctionality = navigateToAccount,
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
            Text("Library Description page")
        }
    }
}