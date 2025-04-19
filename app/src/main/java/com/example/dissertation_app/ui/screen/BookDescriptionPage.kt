package com.example.dissertation_app.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.DeleteConfirmationDialog
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.data.dataset.savedLibraries.SavedLibraries
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.ui.items.LibraryBookUiState
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.LibraryUiState
import com.example.dissertation_app.ui.items.LibraryViewModel
import com.example.dissertation_app.ui.items.SavedLibraryUiState
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination
import kotlin.toString

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
    val libraryViewModel : LibraryViewModel? = LibraryLocation.getLibraryViewModel()
    val savedLibraryViewModel: SavedLibraryViewModel? = LibraryDescriptionLocation.getSavedLibraryViewModel()
    val icon = Icons.Filled.Bookmark

    var alertActive by remember {mutableStateOf(false)}

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
                    alertActive = true
                },
                icon = icon,
                iconDescription = "add to library"
            )
        }

    ) {
        if(alertActive) {
            var book = getBookmarkedBook(
                bookId = bookInformation?.id,
                bookInformation = bookInformation,
                uiState = libraryBookViewModel?.libraryBookUiState!!,
                searchLibrary = libraryBookViewModel::searchLibraryBook,
                addLibraryBook = libraryBookViewModel::addLibraryBook
            )

            Log.d("Bookmarked Book", bookInformation?.id.toString())

            Log.d("Bookmarked Book", book.toString())

            savedLibraryViewModel?.let { savedLibraryViewModel ->
                libraryViewModel?.let { libraryViewModel ->
                    BookMarkAlert(
                        bookId = book?.id ?: -1,
                        uiStateSavedLibrary = savedLibraryViewModel.savedLibraryUiState,
                        uiStateLibrary = libraryViewModel.libraryUiState,
                        addBookMark = savedLibraryViewModel::saveBookInLibrary,
                        removeBookMark = savedLibraryViewModel::removeBookFromLibrary,
                        findLibraries = libraryViewModel::getLibraries,
                        findBooksLibrary = savedLibraryViewModel::getBooksLibraries,
                        onDismiss = {alertActive = false}
                    )
                }
            }
        }
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

    if (bookId == null || bookInformation == null) {
        Log.w("Bookmarked Book", "Missing bookId or bookInformation")
        return null
    }

    searchLibrary(bookId)

    Log.d("Bookmarked Book - After Search", uiState.toString())

    return when (uiState) {
        is LibraryBookUiState.Success -> {
            Log.d("Bookmarked Book", "Found book: ${uiState.libraryBook}")
            uiState.libraryBook
        }
        is LibraryBookUiState.FunctionSuccess -> {
            Log.d("Bookmarked Book", "Book added successfully (FunctionSuccess state).")
            null
        }
        else -> {
            Log.d("Bookmarked Book", "Book not found, attempting to add.")

            val libraryBook = bookInformation.toLibraryBook()
            if (libraryBook != null) {
                addLibraryBook(libraryBook)

                null
            } else {
                Log.w("Bookmarked Book", "Could not convert bookInformation to LibraryBooks")
                null
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BookMarkAlert(
    bookId: Int,
    uiStateSavedLibrary: SavedLibraryUiState?,
    uiStateLibrary: LibraryUiState?,
    addBookMark: (SavedLibraries) -> Unit = {},
    removeBookMark: (Int, Int) -> Unit = { p1: Int, p2: Int -> p1 + p2 },
    findLibraries: () -> Unit = {},
    findBooksLibrary: (Int) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    findLibraries()
    val libraries = when(uiStateLibrary) {
        is LibraryUiState.Success -> uiStateLibrary.libraries
        else -> null
    }

    findBooksLibrary(bookId)
    val booksLibrary = when(uiStateSavedLibrary) {
        is SavedLibraryUiState.Success -> uiStateSavedLibrary.libraries
        else -> null
    }

    BasicAlertDialog(
        onDismissRequest = {onDismiss()},

        modifier = Modifier.padding(5.dp),

        content = {
            Card (
                modifier = Modifier.border(3.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Selected a library to add or remove the bookmarked book: ",
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(10.dp)
                    )

                    Text(
                        text = "Add to these libraries",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(10.dp)
                    )

                    CreateLibraryRow(
                        bookId = bookId,
                        libraries = libraries,
                        addBookMark = addBookMark,
                        onDismiss = onDismiss,
                    )

                    Text(
                        text = "Remove from these Libraries",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(10.dp)
                    )

                    CreateLibraryRow(
                        bookId = bookId,
                        libraries = booksLibrary,
                        removeBookMark = removeBookMark,
                        onDismiss = onDismiss,
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLibraryRow(
    bookId: Int,
    libraries: List<Libraries>?,
    addBookMark: (SavedLibraries) -> Unit = {},
    removeBookMark: (libraryId: Int, bookId : Int) -> Unit = { p1: Int, p2: Int -> p1 + p2 },
    onDismiss: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var alertActive by remember {mutableStateOf(false)}

    if (libraries == null || libraries.isEmpty()) {
        Text(
            text = "No Libraries exist",
            fontSize = 10.sp
        )

    } else {
        LazyHorizontalGrid(
            rows = Fixed(1),
            modifier = Modifier
                .height(50.dp)
                .width(290.dp)
                .border(width = 3.dp, color = Color.Gray)
        ) {
            items(
                count = libraries.size,
                key = { libraryId -> libraries[libraryId].id }
            ) { libraryId ->

                Surface(
                    onClick = if (addBookMark != {}) {
                        {
                            addBookMark(
                                SavedLibraries(
                                    libraryId = libraryId,
                                    bookID = bookId
                                )
                            )

                            onDismiss()
                        }
                    } else {
                        {
                            alertActive = true
                        }
                    },
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(3.dp)
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .size(30.dp)
                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                            contentDescription = "library icon",
                        )

                        Text(
                            text = libraries[libraryId].libraryName,
                            fontSize = 10.sp
                        )
                    }

                    if(alertActive) {
                        DeleteConfirmationDialog(
                            itemName = libraries[libraryId].libraryName,
                            onConfirmDelete = {
                                removeBookMark(
                                    libraryId,
                                    bookId
                                )
                            },
                            onDismissRequest = {
                                alertActive = false
                                onDismiss
                            }
                        )
                    }
                }
            }
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

@Preview
@Composable
fun BookMarkAlertPreview() {
    BookMarkAlert(
        bookId = 1,
        uiStateSavedLibrary = null,
        uiStateLibrary = null,
        removeBookMark = { p1: Int, p2: Int -> p1 + p2 },
    )
}

@Preview
@Composable
fun CreateLibraryRowPreview() {
    val libraries : Array<Libraries> = arrayOf(
        Libraries(
            id = 1,
            libraryName = "Test"
        ),
        Libraries(
            id = 2,
            libraryName = "Test"
        ),
        Libraries(
            id = 3,
            libraryName = "Test"
        ),
        Libraries(
            id = 4,
            libraryName = "Test"
        ),
        Libraries(
            id = 5,
            libraryName = "Test"
        ),
        Libraries(
            id = 6,
            libraryName = "Test"
        ),
        Libraries(
            id = 7,
            libraryName = "Test"
        ),
        Libraries(
            id = 8,
            libraryName = "Test"
        ),
    )

    CreateLibraryRow(
        bookId = 1,
        libraries = libraries.toList()
    )
}
