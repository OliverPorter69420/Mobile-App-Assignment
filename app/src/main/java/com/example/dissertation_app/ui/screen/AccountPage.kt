package com.example.dissertation_app.ui.screen

import android.R.attr.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.dissertation_app.BookTopAppBar
import com.example.dissertation_app.R
import com.example.dissertation_app.ui.navigation.NavigationDestination

object AccountLocation : NavigationDestination {
    override val route = "account"
    override val titleRes = R.string.account_screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navigateToHome: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookTopAppBar(
                title = "Account",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateToHome
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Account Screen")
        }
    }
}