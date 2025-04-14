package com.example.dissertation_app.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryLocation : NavigationDestination {
    override val route = "library"
    override val titleRes = R.string.library_screen
    private var libraryBookViewModel: LibraryBookViewModel? = null
    private var savedLibraryViewModel: SavedLibraryViewModel? = null

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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    navigateToBookDescription: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val libraryBookViewModel: LibraryBookViewModel? = LibraryLocation.getLibraryBookViewModel()
    val bookViewModel: BookViewModel? = SearchLocation.getBookViewModel()

    var showCreateLibrary by remember { mutableStateOf(false) }
    var currentLibraryName by remember { mutableStateOf("") }

    Scaffold(
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
                        currentLibraryName = it
                    },
                    exitFunction = {
                        showCreateLibrary = false
                    }
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
                    removeButtonFunction = {},
                    uploadingButtonFunction = {}
                )
            }
        },
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            if (currentLibraryName != "") {
                Text(text = currentLibraryName)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewLibraries(
    modifier: Modifier = Modifier,
    circularButtonFunction: () -> Unit,
    removeButtonFunction: () -> Unit,
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
            onClick = removeButtonFunction,
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
    exitFunction: () -> Unit = {}
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
    CreateLibrary()
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