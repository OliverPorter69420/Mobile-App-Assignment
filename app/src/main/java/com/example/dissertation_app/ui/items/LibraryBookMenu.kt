package com.example.dissertation_app.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.ui.screen.LibraryLocation

@Composable
fun LibraryBookMenu(
    uiState: SavedLibraryUiState,
    navigateToBookDescription: () -> Unit = {},
    getBooks: (Int) -> Unit = {},
    deleteBook: (Int,Int) -> Unit
) {
    val currentIndex = LibraryLocation.viewCurrentLibraryIndex()

    getBooks(currentIndex)

    when (uiState) {
        is SavedLibraryUiState.Success -> LibraryBookGrid(
            modifier = Modifier,
            libraryBooks = uiState.libraryBooks,
            currentIndex = currentIndex,
            getBooks = getBooks(currentIndex),
            deleteBooks = deleteBook,
            navigateToBookDescription = navigateToBookDescription
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
    modifier: Modifier,
    libraryBooks: List<LibraryBooks>?,
    currentIndex: Int,
    getBooks: Unit,
    deleteBooks: (Int, Int) -> Unit,
    navigateToBookDescription: () -> Unit,
) {
    LazyVerticalGrid(
        columns = Fixed(1),
        modifier = modifier.padding(10.dp)
            .fillMaxSize()
    ) {
        items(
            count = libraryBooks?.size ?: 0,
            key = {libraryBookId -> libraryBooks?.get(libraryBookId)?.id!!}
        ) {libraryBooksId ->
            CreateLibraryBookBox(
                libraryBook = libraryBooks?.get(libraryBooksId)!!,
                getBooks = getBooks,
                deleteBooks = deleteBooks(currentIndex, libraryBooksId),
                navigateToBookDescription = navigateToBookDescription
            )
        }
    }
}

@Composable
fun CreateLibraryBookBox(
    libraryBook: LibraryBooks,
    getBooks: Unit,
    deleteBooks: Unit,
    navigateToBookDescription: () -> Unit
) {
    val title = libraryBook.title
    val authors = libraryBook.author
    val description = libraryBook.description
    val imageUrl = libraryBook.imageUrl

    Box(
        modifier = Modifier.padding(10.dp)
            .background(Color.LightGray)
    ) {  }
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

@Preview(showBackground = true)
@Composable
fun LibraryBookBoxPreview() {

}