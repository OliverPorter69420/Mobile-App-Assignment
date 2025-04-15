package com.example.dissertation_app.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.ui.items.AddNewLibraries
import com.example.dissertation_app.ui.items.CreateLibrary
import com.example.dissertation_app.ui.items.CreateLibraryGrid
import com.example.dissertation_app.ui.items.LibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryLocation : NavigationDestination {
    override val route = "library"
    override val titleRes = R.string.library_screen
    private var selectLibraryIndex: Int by mutableIntStateOf(-1)
    private var libraryViewModel: LibraryViewModel? = null

    fun selectLibraryIndex(id:Int) {
        selectLibraryIndex = id
    }

    fun viewCurrentLibraryIndex(): Int {
        return selectLibraryIndex
    }

    fun getLibraryViewModel(): LibraryViewModel? {
        return libraryViewModel
    }

    fun libraryViewModel(viewModel: LibraryViewModel?) {
        libraryViewModel = viewModel
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    navigateToHome: () -> Unit,
    navigateToLibraryDescription: () -> Unit,
) {
    val libraryViewModel : LibraryViewModel? = LibraryLocation.getLibraryViewModel()

    var showCreateLibrary by remember { mutableStateOf(false) }

    libraryViewModel?.getLibraries()

    Scaffold(
        topBar = {
            BookTopAppBar(
                title = "Library Screen",
                canNavigateBack = true,
                navigateUp = navigateToHome
            )
        },

        /*todo add in functionality to add a new library and then redo the add book functionality to add a new book to the library, this might take a long time to redo all the already existing functions but eh whatever it will make it all look nicer/cleaner/all that in the future */

        bottomBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                CreateLibrary(
                    createFunction = {
                        libraryViewModel.addLibrary(
                            Libraries(libraryName = it)
                        )
                    },
                    exitFunction = {
                        showCreateLibrary = false
                    },

                    reloadFunction = libraryViewModel!!::getLibraries
                )
            }

            AnimatedVisibility(
                visible = !showCreateLibrary,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                AddNewLibraries(
                    circularButtonFunction = {
                        showCreateLibrary = true
                    },
                    removeButtonFunction = {
                        libraryViewModel.removeLibrary(
                            libraryId = it
                        )
                    },
                    uploadingButtonFunction = {},
                    reloadFunction = libraryViewModel!!::getLibraries
                )
            }
        },
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CreateLibraryGrid(
                librariesUiStates = libraryViewModel?.libraryUiState,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    LibraryScreen(
        navigateToHome = {},
        navigateToLibraryDescription = {}
    )
}