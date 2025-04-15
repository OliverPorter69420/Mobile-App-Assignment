package com.example.dissertation_app.ui.items

import androidx.compose.runtime.Composable

@Composable
fun LibraryBookMenu(
    uiState: SavedLibraryUiState,
    navigateToBookDescription: () -> Unit = {},
    getBooks: (Int) -> Unit = {},
    deleteBook: (Int,Int) -> Unit
) {
}