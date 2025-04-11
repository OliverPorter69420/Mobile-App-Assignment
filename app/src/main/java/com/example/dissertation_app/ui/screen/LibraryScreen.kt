package com.example.dissertation_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.BookFragment
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object LibraryLocation : NavigationDestination {
    override val route = "library"
    override val titleRes = R.string.library_screen
    private var libraryBookViewModel: LibraryBookViewModel? = null

    fun getLibraryBookViewModel(): LibraryBookViewModel? {
        return libraryBookViewModel
    }

    fun libraryBookViewModel(viewModel: LibraryBookViewModel?) {
        libraryBookViewModel = viewModel
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
    val bookFragment = BookFragment(libraryBookViewModel!!)

    Scaffold (
        topBar = {
            BookTopAppBar(
                title = "Library Screen",
                canNavigateBack = true,
                navigateUp = navigateToHome
            )
        },

        bottomBar = {
            AddNewLibraries(
                circularButtonFunction = {},
                removeButtonFunction = {},
                uploadingButtonFunction = {}
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Library Screen")
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
        .padding(10.dp)
        .background(Color.Gray),

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

@Preview
@Composable
fun AddNewLibrariesPreview() {
    AddNewLibraries(
        circularButtonFunction = {},
        removeButtonFunction = {},
        uploadingButtonFunction = {}
    )
}