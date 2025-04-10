package com.example.dissertation_app.ui.screen

import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.BookFragment
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryLocation : NavigationDestination {
    override val route = "library"
    override val titleRes = R.string.library_screen
    private var libraryBookViewModel: LibraryBookViewModel? = null

    fun getLibraryBookViewModel(): LibraryBookViewModel? {
        return libraryBookViewModel
    }

    fun libraryBookViewModel(viewModel: LibraryBookViewModel?) {
        libraryBookViewModel = viewModel
    }
}

class LibraryScreen(
    navigateToBookDescription: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val libraryBookViewModel: LibraryBookViewModel? = LibraryLocation.getLibraryBookViewModel()
    val bookViewModel: BookViewModel? = SearchLocation.getBookViewModel()

    val bookFragment = BookFragment(libraryBookViewModel!!)
}