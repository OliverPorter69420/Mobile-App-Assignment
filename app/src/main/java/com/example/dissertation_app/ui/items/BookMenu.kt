package com.example.dissertation_app.ui.items

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scale
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.Transformation
import com.example.dissertation_app.R
import com.example.dissertation_app.model.BookObjects
import com.example.dissertation_app.ui.screen.BookDescriptionLocation
import com.example.dissertation_app.ui.screen.FragmentActivity
import com.example.dissertation_app.ui.screen.MainContentFragment
import com.example.dissertation_app.ui.screen.SearchLocation


@Composable
fun BookMenu(
    bookUiState: BookUiState,
    getBooks: () -> Unit,
    getTextBarFunctionality: (String) -> Unit,
    resetAction: () -> Unit,
    navigateToBookDescription: () -> Unit,
    modifier: Modifier,
){
    when (bookUiState) {

        is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())

        is BookUiState.Success -> BookGridScreen(
            bookUiState.bookSearch,
            bookUiState.thumbnails,
            resetAction = resetAction,
            navigateToBookDescription = navigateToBookDescription,
            modifier = modifier
        )

        is BookUiState.Empty -> EmptyScreen(modifier = modifier)

        is BookUiState.Start -> StartScreen(
            updateSearchTerm = getTextBarFunctionality,
            getBooks = getBooks,
            modifier = modifier
        )

        else -> ErrorScreen(retryAction = getBooks)
    }
}

@Composable
fun StartScreen(
    updateSearchTerm: (String) -> Unit,
    getBooks: () -> Unit,
    modifier: Modifier,
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBar(
            onUpdateSearchTerm = updateSearchTerm,
            getBooks = getBooks,
            modifier = modifier
        )

        Text(text = "Enter the title of the book you want to view")
    }
}

@Composable
fun TextBar(
    onUpdateSearchTerm: (String) -> Unit,
    getBooks: () -> Unit,
    modifier: Modifier
) {
    var inputText by rememberSaveable { mutableStateOf("") }
    var searchTerm by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(inputText)
        Text(searchTerm)

        TextField(
            value = inputText,
            onValueChange = { newText ->
                inputText = newText
            },
            modifier = modifier,
            label = { Text("Enter Text") },
            singleLine = true,
        )

        Button(onClick = {
            onUpdateSearchTerm(inputText)
            getBooks()
        }
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Column {
        Text(text = "loading")
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column {
        Text(text = "error")
    }
}

@Composable
fun EmptyScreen(modifier: Modifier) {
    Column {
        Text(text = "No results, search for something else")
    }
}

@Composable
fun BookGridScreen(
    bookSearch: List<BookObjects>?,
    thumbnails: MutableList<String>,
    resetAction: () -> Unit,
    navigateToBookDescription: () -> Unit,
    modifier: Modifier,
) {
    val fragmentActivity = FragmentActivity
    val viewModel = SearchLocation.getBookViewModel()

    if (viewModel != null) {
        fragmentActivity.showChildFragment(BookGridFragment(viewModel))
    }

    var selectedBookID: String? by remember { mutableStateOf<String?>(null) }

    if (selectedBookID == null) {

        Column {

            Button(
                onClick = resetAction,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Reset")
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = modifier.padding(4.dp),
            ) {
                items(
                    count = thumbnails.size,
                    key = { thumbnail -> thumbnails[thumbnail] },

                    ) { thumbnail ->
                    PhotoLoader(
                        imageLinks = thumbnails[thumbnail],
                        bookDescription = bookSearch?.get(thumbnail)?.volumeInfo?.title.toString(),
                        onViewBookDetails = {
                            selectedBookID = thumbnail.toString()
                            navigateToBookDescription()
                        },
                        onChangeBookDescription = {
                            BookDescriptionLocation.bookInformation(bookSearch?.get(thumbnail))
                        },
                        modifier = modifier
                            .padding(2.dp)
                            .fillMaxSize()
                            .aspectRatio(0.5f)
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoLoader (
    imageLinks: String?,
    bookDescription: String?,
    onChangeBookDescription: () -> Unit,
    onViewBookDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {
            onChangeBookDescription()
            onViewBookDetails()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = bookDescription.toString())

            Card(
                modifier = modifier,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            ) {
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

                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(imageLinks?.replace("http", "https"))
                        .crossfade(true)
                        .size(200, 400)
                        .transformations(CustomScaleTransformation(200, 400))
                        .build(),
                    imageLoader = imageLoader,
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = bookDescription ?: "book image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(15.dp))
                )
            }
        }
    }
}

class CustomScaleTransformation(private val targetWidth: Int, private val targetHeight: Int) : Transformation {
    override val cacheKey: String = "scale_${targetWidth}_${targetHeight}"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        return input.scale(targetWidth, targetHeight)
    }
}