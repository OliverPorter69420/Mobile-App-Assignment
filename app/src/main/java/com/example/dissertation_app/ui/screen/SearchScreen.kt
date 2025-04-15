package com.example.dissertation_app.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.items.BookMenu
import com.example.dissertation_app.ui.items.BookViewModel
import com.example.dissertation_app.ui.items.LibraryBookViewModel
import com.example.dissertation_app.ui.navigation.NavigationDestination

object SearchLocation : NavigationDestination {
    override val route = "search"
    override val titleRes = R.string.search_screen
    private var bookViewModel: BookViewModel? = null

    fun getBookViewModel(): BookViewModel? {
        return bookViewModel
    }

    fun bookViewModel(viewModel: BookViewModel?) {
        bookViewModel = viewModel
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen (
    navigateToBookDescription: () -> Unit,
    backToHome: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookViewModel: BookViewModel? = SearchLocation.getBookViewModel()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BookTopAppBar(
                    title = "Search",
                    canNavigateBack = true,
                    scrollBehavior = scrollBehavior,
                    navigateUp = backToHome
                )

                Button(
                    onClick = {
                        Log.d("SearchScreen", "Account button clicked")
                    }
                ) {
                    Text(text = "account")
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                bookViewModel?.let { bookViewModel ->
                    BookMenu(
                        bookUiState = bookViewModel.bookUiState,
                        getBooks = bookViewModel::getBooks,
                        getTextBarFunctionality = bookViewModel::updateSearchTerm,
                        resetAction = bookViewModel::resetSearchTerm,
                        navigateToBookDescription = navigateToBookDescription,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}

