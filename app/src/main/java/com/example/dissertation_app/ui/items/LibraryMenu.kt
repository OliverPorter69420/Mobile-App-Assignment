package com.example.dissertation_app.ui.items

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.ui.screen.LibraryLocation

@Composable
fun CreateLibraryGrid(
    librariesUiStates: LibraryUiState?,
    navigateToLibraryDescription: () -> Unit = {}
) {
    when (librariesUiStates) {
        is LibraryUiState.Success -> {
            LazyVerticalGrid(
                columns = Fixed(2),
                modifier = Modifier.padding(10.dp)
            ) {
                items(
                    count = librariesUiStates.libraries?.size ?: 0,
                    key = { libraryId -> librariesUiStates.libraries?.get(libraryId)?.id!! },
                ) { libraryId ->
                    CreateLibraryCard(
                        library = librariesUiStates.libraries?.get(libraryId)!! ,
                        navigateToLibraryDescription = navigateToLibraryDescription
                    )
                }
            }
        }
        is LibraryUiState.Loading -> Text(text = "Loading")
        is LibraryUiState.Error -> Text(text = "Error")
        null -> Text(text = "null")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLibraryCard(
    library: Libraries,
    navigateToLibraryDescription: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color.Cyan else Color.LightGray,
        label = "Background Color Animation"
    )
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Surface(
        modifier = Modifier
            .padding(1.dp)

            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        if (!isPressed) {
                            Log.d("isPressed", "$isPressed")

                            navigateToLibraryDescription()
                        }
                    },

                    onPress = {
                        try {
                            isPressed = true
                            LibraryLocation.selectLibraryIndex(library.id)
                            tryAwaitRelease()
                        } finally {
                            isPressed = false
                        }
                    }
                )
            },
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.size(100.dp)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),

                colors = CardColors(
                    containerColor = backgroundColor,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Cyan,
                    disabledContentColor = Color.Black
                )
            ) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                    contentDescription = "library image"
                )

                Text(text = library.libraryName)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewLibraries(
    modifier: Modifier = Modifier,
    circularButtonFunction: () -> Unit = {},
    removeButtonFunction: (Int) -> Unit = {},
    uploadingButtonFunction: () -> Unit = {},
    reloadFunction: () -> Unit = {}
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
                reloadFunction()
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
    reloadFunction: () -> Unit = {}
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
