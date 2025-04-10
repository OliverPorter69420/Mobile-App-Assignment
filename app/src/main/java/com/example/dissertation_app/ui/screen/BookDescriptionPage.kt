package com.example.dissertation_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.model.VolumeInfo
import com.example.dissertation_app.ui.navigation.NavigationDestination

object BookDescriptionLocation : NavigationDestination {
    override val route = "bookDescription"
    override val titleRes = R.string.book_description_screen
    private var bookInformation: VolumeInfo? = null

    fun bookInformation(bookInformation: VolumeInfo) {
        this.bookInformation = bookInformation
    }

    fun getBookInformation(): VolumeInfo? {
        return bookInformation
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDescriptionPage(
    backToSearch : () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bookInformation = BookDescriptionLocation.getBookInformation()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),

        topBar = {
            BookTopAppBar(
                title = "Book Information",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = backToSearch,
                buttonFunctionality = {/*TODO add in the insert library book functionality here */}
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {

            Row (
                modifier = Modifier.padding(10.dp)
                    .align(CenterHorizontally)
                    .width(1000.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            }

            Text(
                bookInformation?.title.toString(),
                modifier = Modifier.padding(10.dp)
            )

            Text(
                bookInformation?.authors.toString(),
                modifier = Modifier.padding(10.dp)
            )

            Text(
                bookInformation?.description.toString(),
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}