package com.example.dissertation_app.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.Coil.imageLoader
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.libraryBook.LibraryBooks
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.model.VolumeInfo
import com.example.dissertation_app.ui.screen.BookDescriptionLocation
import com.example.dissertation_app.ui.screen.LibraryLocation
import com.example.dissertation_app.ui.screen.SearchLocation
import kotlin.collections.joinToString

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
        modifier = modifier
            .fillMaxSize()
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
        modifier = modifier
            .fillMaxSize()
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
        modifier = modifier
            .padding(10.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLibraryBookBox(
    libraryBook: LibraryBooks,
    getBooks: Unit?,
    deleteBooks:  Unit?,
    navigateToBookDescription: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val title = libraryBook.title
    val authors = libraryBook.author
    val description = libraryBook.description
    val imageUrl = libraryBook.imageUrl

    val viewModel = SearchLocation.getBookViewModel()
    val uiState = viewModel?.bookUiState

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.25)
                .build()
        }

        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }

        .build()

    Surface(
        onClick = {
            viewModel?.getBookDetails(libraryBook.bookId)!!

            val bookInformation : BookObjects? = when(uiState) {
                is BookUiState.Success -> {
                    uiState.bookSearch?.get(0)
                }

                else -> {
                    null
                }
            }

            BookDescriptionLocation.bookInformation(
                bookInformation = bookInformation
            )

            navigateToBookDescription
        }
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.LightGray)
        ) {
            IconButton(
                onClick = {
                    deleteBooks
                    getBooks
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "close",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            Row(
                modifier = Modifier.padding(10.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.size(150.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(imageUrl.replace("http", "https"))
                            .crossfade(true)
                            .size(200, 400)
                            .transformations(CustomScaleTransformation(200, 400))
                            .build(),
                        imageLoader = imageLoader,
                        error = painterResource(R.drawable.ic_broken_image),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = "book cover for $title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                            .clip(RoundedCornerShape(15.dp))
                    )
                }

                Column(
                    modifier = Modifier.padding(10.dp)
                        .size(200.dp)
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(2.dp)
                    )

                    Text(
                        text = authors,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(2.dp)
                    )

                    Text(
                        text = description,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
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
    val libraryBook : Array<LibraryBooks> = arrayOf(
        LibraryBooks(
            id = 1,
            bookId = "test",
            title = "title text",
            author = "Authors name",
            description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
            imageUrl = "test"
        ),
        LibraryBooks(
            id = 2,
            bookId = "test",
            title = "title text",
            author = "Authors name",
            description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
            imageUrl = "test"
        ),
        LibraryBooks(
            id = 3,
            bookId = "test",
            title = "title text",
            author = "Authors name",
            description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
            imageUrl = "test"
        ),
        LibraryBooks(
            id = 4,
            bookId = "test",
            title = "title text",
            author = "Authors name",
            description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
            imageUrl = "test"
        ),
        LibraryBooks(
            id = 5,
            bookId = "test",
            title = "title text",
            author = "Authors name",
            description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
            imageUrl = "test"
        ),
        LibraryBooks(
            id = 6,
            bookId = "test",
            title = "title text",
            author = "Authors name",
            description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
            imageUrl = "test"
        )
    )

    LibraryBookGrid(
        modifier = Modifier,
        libraryBooks = libraryBook.toList(),
        currentIndex = 1,
        getBooks = Unit,
        deleteBooks = {p1 : Int, p2: Int -> p1 + p2},
        navigateToBookDescription = {}
    )
}

@Preview
@Composable
fun LibraryBookBoxPreview() {
    val libraryBook : LibraryBooks = LibraryBooks(
        id = 1,
        bookId = "test",
        title = "title text",
        author = "Authors name",
        description = "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test",
        imageUrl = "test"
    )
    
    CreateLibraryBookBox(
        libraryBook = libraryBook,
        getBooks = null,
        deleteBooks = null,
        navigateToBookDescription = {}
    )
}