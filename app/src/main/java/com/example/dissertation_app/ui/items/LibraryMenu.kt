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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.data.dataset.library.Libraries
import com.example.dissertation_app.ui.screen.LibraryDescriptionLocation
import com.example.dissertation_app.ui.screen.LibraryLocation
import kotlinx.coroutines.delay

@Composable
fun CreateLibraryGrid(
    librariesUiStates: LibraryUiState?,
    navigateToLibraryDescription: () -> Unit = {}
) {
    when (librariesUiStates) {
        is LibraryUiState.Success -> {
            CreateGridStructure(
                libraries = librariesUiStates.libraries,
                navigateToLibraryDescription = navigateToLibraryDescription
            )
        }

        else -> {
            Column {
                Text(
                    text = "No libraries exist, maybe create one?"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGridStructure(
    libraries : List<Libraries>?,
    navigateToLibraryDescription: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LazyVerticalGrid(
        columns = Fixed(2),
        modifier = Modifier.padding(10.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        items(
            count = libraries?.size ?: 0,
            key = { libraryId -> libraries?.get(libraryId)?.id!! },
        ) { libraryId ->
            CreateLibraryCard(
                library = libraries?.get(libraryId)!!,
                navigateToLibraryDescription = navigateToLibraryDescription
            )
        }
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

    Surface(
        modifier = Modifier
            .padding(10.dp)

            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        if (!isPressed) {
                            LibraryDescriptionLocation.selectLibraryIndex(library.id)

                            navigateToLibraryDescription()
                        }
                    },


                    onPress = {
                        val pressStartTime = System.currentTimeMillis()
                        isPressed = false
                        try {
                            while (System.currentTimeMillis() - pressStartTime < 200L) {
                                delay(20L)
                            }
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
        color = backgroundColor,
        shadowElevation = 4.dp
    ) {
        Card(
            modifier = Modifier.size(100.dp),
            colors = CardColors(
                containerColor = backgroundColor,
                contentColor = Color.Black,
                disabledContainerColor = Color.Cyan,
                disabledContentColor = Color.Black
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                    contentDescription = "library image",
                    contentScale = ContentScale.FillBounds
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

@Preview(showBackground = true)
@Composable
fun CreateGridPreview(){
    val libraries : Array<Libraries> = arrayOf(
        Libraries(
            id = 1,
            libraryName = "test"
        ),
        Libraries(
            id = 2,
            libraryName = "test"
        ),
        Libraries(
            id = 3,
            libraryName = "test"
        ),
        Libraries(
            id = 4,
            libraryName = "test"
        ),
        Libraries(
            id = 5,
            libraryName = "test"
        ),
        Libraries(
            id = 6,
            libraryName = "test"
        ),
        Libraries(
            id = 7,
            libraryName = "test"
        ),
        Libraries(
            id = 8,
            libraryName = "test"
        ),
    )
    CreateGridStructure(
        libraries = libraries.toList(),
        navigateToLibraryDescription = {}
    )
}
