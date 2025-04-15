package com.example.dissertation_app.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LibraryBookMenu(
    uiState: SavedLibraryUiState,
    navigateToBookDescription: () -> Unit = {},
    getBooks: (Int) -> Unit = {},
    deleteBook: (Int,Int) -> Unit
) {

    when (uiState) {
        is SavedLibraryUiState.Success -> LibraryBookGrid(
            modifier = Modifier
        )

        SavedLibraryUiState.Empty -> EmptyLibraryBookScreen(
            modifier = Modifier
        )

        SavedLibraryUiState.Error -> ErrorScreenBookScreen(
            modifier = Modifier
        )
    }
        /* todo finish this part of the program and then improve the add library book functionality
    * to add books to the library the user chooses and then add some small fixes (this is all
    * for adding accounts in later on after this is all sorted*/

}

@Composable
fun ErrorScreenBookScreen(
    modifier : Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Error loading this Library, try again!")
    }
}

@Composable
fun EmptyLibraryBookScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("No book within this Library, maybe add some into the library")
    }
}

@Composable
fun LibraryBookGrid(
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = Fixed(1),
        modifier = Modifier.padding(10.dp)
    ) {
        
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenBookScreenPreview() {
    ErrorScreenBookScreen(
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun EmptyLibraryBookScreenPreview() {
    EmptyLibraryBookScreen(
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LibraryBookGridPreview() {

}