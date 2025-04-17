package com.example.dissertation_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibraries
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.ui.items.LibraryBookUiState
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object BookDescriptionLocation : NavigationDestination {
    override val route = "bookDescription"
    override val titleRes = R.string.book_description_screen
    private var bookInformation: BookObjects? = null
    private var libraryBookViewModel: LibraryBookViewModel? = null

    fun bookInformation(bookInformation: BookObjects?) {
        this.bookInformation = bookInformation
    }

    fun getBookInformation(): BookObjects? {
        return bookInformation
    }


    fun getLibraryBookViewModel(): LibraryBookViewModel? {
        return libraryBookViewModel
    }

    fun libraryBookViewModel(viewModel: LibraryBookViewModel?) {
        libraryBookViewModel = viewModel
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDescriptionPage(
    backToSearch : () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookInformation = BookDescriptionLocation.getBookInformation()
    val libraryBookViewModel: LibraryBookViewModel? = BookDescriptionLocation.getLibraryBookViewModel()
    val savedLibraryViewModel: SavedLibraryViewModel? = LibraryDescriptionLocation.getSavedLibraryViewModel()
    val icon = Icons.Filled.Bookmark

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),

        topBar = {
            BookTopAppBar(
                title = "Book Information",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = backToSearch,
                iconButtonFunctional = true,
                composableButtonFunctionality = {

                    var book = getBookmarkedBook(
                        bookId = bookInformation?.id,
                        bookInformation = bookInformation,
                        uiState = libraryBookViewModel?.libraryBookUiState!!,
                        searchLibrary = libraryBookViewModel::searchLibraryBook,
                        addLibraryBook = libraryBookViewModel::addLibraryBook
                    )

                    BookMarkAlert(
                        bookId = book?.id!!,
                        addBookMark = savedLibraryViewModel!!::saveBookInLibrary,
                        removeBookMark = savedLibraryViewModel::removeBookFromLibrary
                    )
                },
                icon = icon,
                iconDescription = "add to library"
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {

            Row (
                modifier = Modifier
                    .padding(10.dp)
                    .align(CenterHorizontally)
                    .width(1000.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            }

            Text(
                bookInformation?.volumeInfo?.title.toString(),
                modifier = Modifier.padding(10.dp)
            )

            Text(
                bookInformation?.volumeInfo?.authors.toString(),
                modifier = Modifier.padding(10.dp)
            )

            Text(
                bookInformation?.volumeInfo?.description.toString(),
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

fun getBookmarkedBook(
    searchLibrary: (String) -> Unit,
    uiState: LibraryBookUiState,
    bookId: String?,
    bookInformation: BookObjects?,
    addLibraryBook: (LibraryBooks) -> Unit,
) : LibraryBooks? {
    searchLibrary(bookId!!)

    when(uiState) {
        is LibraryBookUiState.Success -> {
            null
        }
        else -> {
            addLibraryBook(
                bookInformation.toLibraryBook()!!
            )
            searchLibrary(bookId)
        }
    }

    return when(uiState) {
        is LibraryBookUiState.Success -> uiState.libraryBook
        else -> {
            null
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookMarkAlert(
    bookId: Int,
    addBookMark: (SavedLibraries) -> Unit,
    removeBookMark: (Int, Int) -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = TODO(),
        modifier = TODO(),
        properties = TODO(),
        content = TODO()
    )

    /*todo add an alert which will allow you to add the bookmarked booked to a specfic library*/
}

private fun BookObjects?.toLibraryBook() : LibraryBooks? {
    return if (this == null) {
        null
    } else {
        LibraryBooks(
            bookId = this.id?.toString() ?: "null",
            title = this.volumeInfo?.title?.toString() ?: "null",
            author = this.volumeInfo?.authors?.joinToString(",") ?: "null",
            description = this.volumeInfo?.description?.toString() ?: "null",
            imageUrl = this.volumeInfo?.imageLinks?.thumbnail?.toString() ?: "null",
        )
    }
}
