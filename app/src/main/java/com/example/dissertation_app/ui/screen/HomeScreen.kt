package com.example.dissertation_app.ui.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.items.LibraryViewModel
import com.example.dissertation_app.ui.items.SavedLibraryViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object HomeLocation : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToLibrary: () -> Unit,
    navigateToAccount: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookViewModel: BookViewModel? = viewModel(factory = BookViewModel.Factory)
    SearchLocation.bookViewModel(bookViewModel)

    val libraryBookViewModel: LibraryBookViewModel? = viewModel(factory = LibraryBookViewModel.Factory)
    BookDescriptionLocation.libraryBookViewModel(libraryBookViewModel)

    val savedLibraryViewModel : SavedLibraryViewModel? = viewModel(factory = SavedLibraryViewModel.Factory)
    LibraryDescriptionLocation.savedLibraryViewModel(savedLibraryViewModel)

    val libraryViewModel : LibraryViewModel? = viewModel(factory = LibraryViewModel.factory)
    LibraryLocation.libraryViewModel(libraryViewModel)

    val icon = Icons.Filled.Person

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookTopAppBar(
                title = "Main Screen",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                iconButtonFunctional = true,
                buttonFunctionality = navigateToAccount,
                icon = icon,
                iconDescription = "account"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App logo",
                modifier = Modifier.padding(10.dp)
            )

            Button(
                onClick = navigateToSearch
            ) {
                Text(text = "Search Screen")
            }

            Button(
                onClick = navigateToLibrary
            ) {
                Text(text = "Library Screen")
            }

            Button(
                onClick = navigateToAccount
            ) {
                Text(text = "Account Screen")
            }
        }
    }

}