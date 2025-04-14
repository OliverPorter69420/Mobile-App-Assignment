package com.example.dissertation_app.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.LibraryUiState
import com.example.dissertation_app.ui.items.LibraryViewModel
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryLocation : NavigationDestination {
    override val route = "library"
    override val titleRes = R.string.library_screen
    private var selectLibraryIndex: Int by mutableIntStateOf(-1)
    private var libraryBookViewModel: LibraryBookViewModel? = null
    private var savedLibraryViewModel: SavedLibraryViewModel? = null
    private var libraryViewModel: LibraryViewModel? = null

    fun selectLibraryIndex(id:Int) {
        selectLibraryIndex = id
    }

    fun viewCurrentLibraryIndex(): Int {
        return selectLibraryIndex
    }

    fun getLibraryBookViewModel(): LibraryBookViewModel? {
        return libraryBookViewModel
    }

    fun libraryBookViewModel(viewModel: LibraryBookViewModel?) {
        libraryBookViewModel = viewModel
    }

    fun getSavedLibraryViewModel(): SavedLibraryViewModel? {
        return savedLibraryViewModel
    }

    fun savedLibraryViewModel(viewModel: SavedLibraryViewModel?) {
        savedLibraryViewModel = viewModel
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
    navigateToBookDescription: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val libraryBookViewModel: LibraryBookViewModel? = LibraryLocation.getLibraryBookViewModel()
    val savedLibraryViewModel: SavedLibraryViewModel? = LibraryLocation.getSavedLibraryViewModel()
    val libraryViewModel : LibraryViewModel? = LibraryLocation.getLibraryViewModel()
    val bookViewModel: BookViewModel? = SearchLocation.getBookViewModel()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var showCreateLibrary by remember { mutableStateOf(false) }

    libraryViewModel?.getLibraries()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

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
                        libraryViewModel?.addLibrary(
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
                        libraryViewModel?.removeLibrary(
                            libraryId = it
                        )
                    },
                    uploadingButtonFunction = {}
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

@Composable
private fun CreateLibraryGrid(
    librariesUiStates: LibraryUiState?
) {
    when (librariesUiStates) {
        is LibraryUiState.Success -> {
            LazyVerticalGrid(
                columns = Fixed(2),
                modifier = Modifier.padding(10.dp)
            ) {
                items(
                    count = librariesUiStates.libraries?.size ?: 0,
                    key = { libraryId -> librariesUiStates.libraries?.get(libraryId)!! },
                ) { libraryId ->
                    CreateLibraryCard(
                        library = librariesUiStates.libraries?.get(libraryId)!! ,
                        navigateToBookDescription = {
                            /*todo add a Library description screen that shows the books in the library*/
                        }
                    )
                }
            }
        }
        is LibraryUiState.Loading -> Text(text = "Loading")
        is LibraryUiState.Error -> Text(text = "Error")
        null -> Text(text = "null")
    }
}

@Composable
fun CreateLibraryCard(
    library: Libraries,
    navigateToBookDescription: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(10.dp)
            .background(
                if (isPressed) {
                    Color.Blue
                } else {
                    Color.Gray
                }
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        if (!isPressed) {
                            navigateToBookDescription()
                        }
                    },

                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        LibraryLocation.selectLibraryIndex(id = library.id)
                        isPressed = false
                    }
                )

                Log.d("isPressed", "$isPressed")
            }
    ) {
        Card(modifier = Modifier) {
            Image(
                imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                contentDescription = "library image"
            )

            Text(text = library.libraryName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewLibraries(
    modifier: Modifier = Modifier,
    circularButtonFunction: () -> Unit,
    removeButtonFunction: (Int) -> Unit,
    uploadingButtonFunction: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(Color.Gray)
            .border(1.dp, Color.White)
            .height(200.dp)
            .width(500.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = uploadingButtonFunction,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.CloudDownload,
                contentDescription = "sync library with the database",
                tint = Color.White
            )
        }

        IconButton(
            onClick = circularButtonFunction,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "add new libraries",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }

        IconButton(
            onClick = {
                val libraryId = LibraryLocation.viewCurrentLibraryIndex()
                removeButtonFunction(libraryId)
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "cancel adding new libraries",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLibrary(
    modifier: Modifier = Modifier,
    createFunction: (String) -> Unit = {},
    exitFunction: () -> Unit = {},
    reloadFunction: () -> Unit
) {
    var textValue by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .background(Color.Gray)
            .border(1.dp, Color.White)
            .height(200.dp)
            .width(500.dp)
    ) {
        IconButton(
            onClick = exitFunction,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "close",
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Create Library",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            TextField(
                value = textValue,
                onValueChange = {
                    textValue = it
                    Log.d("LibraryScreen", "Library name: $textValue")
                },
                placeholder = { Text("Enter library name") },
                modifier = Modifier
                    .background(Color.White)
            )
        }

        IconButton(
            onClick = {
                createFunction(textValue)
                reloadFunction()
                textValue = ""
                exitFunction()
            },
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add",
            tint = Color.White,
            modifier = Modifier
                .size(30.dp)
        )
        }
    }
}


@Preview
@Composable
fun CreateLibraryPreview() {
    CreateLibrary(
        createFunction = {},
        exitFunction = {},
        reloadFunction = {}
    )
}

@Preview
@Composable
fun AddNewLibrariesPreview() {
    AddNewLibraries(
        circularButtonFunction = {},
        removeButtonFunction = {},
        uploadingButtonFunction = {}
    )
}