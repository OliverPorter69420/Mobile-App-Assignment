package com.example.dissertation_app.ui.screen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object HomeLocation : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToAccount: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookViewModel: BookViewModel? = viewModel(factory = BookViewModel.Factory)
    SearchLocation.bookViewModel(bookViewModel)

    val libraryBookViewModel: LibraryBookViewModel? = viewModel(factory = LibraryBookViewModel.Factory)
    LibraryLocation.libraryBookViewModel(libraryBookViewModel)

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

            Button(
                onClick = navigateToSearch
            ) {
                Text(text = "Search Screen")
            }
        }
    }

}