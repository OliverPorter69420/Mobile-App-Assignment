package com.example.dissertation_app.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
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
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object BookDescriptionLocation : NavigationDestination {
    override val route = "bookDescription"
    override val titleRes = R.string.book_description_screen
    private var bookInformation: BookObjects? = null

    fun bookInformation(bookInformation: BookObjects?) {
        this.bookInformation = bookInformation
    }

    fun getBookInformation(): BookObjects? {
        return bookInformation
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDescriptionPage(
    backToSearch : () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookInformation = BookDescriptionLocation.getBookInformation()
    val libraryBookViewModel: LibraryBookViewModel? = LibraryDescriptionLocation.getLibraryBookViewModel()
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
                buttonFunctionality = {
                    val book = bookInformation.toLibraryBook()

                    if (book != null) {
                        libraryBookViewModel?.addLibraryBook(book)
                    } else {
                        Log.d("BookDescriptionPage", "book is null")
                    }

                    /*todo: make it so that bookmarks can only be added/removed from the library*/
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
                modifier = Modifier.padding(10.dp)
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
