package com.example.dissertation_app.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.dissertation_app.ui.screen.BookDescriptionLocation
import com.example.dissertation_app.ui.screen.BookDescriptionPage
import com.example.dissertation_app.ui.screen.HomeLocation
import com.example.dissertation_app.ui.screen.HomeScreen
import com.example.dissertation_app.ui.screen.SearchLocation
import com.example.dissertation_app.ui.screen.SearchScreen
import androidx.navigation.compose.composable
import com.example.dissertation_app.ui.items.BookViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import okhttp3.internal.platform.android.AndroidSocketAdapter.Companion.factory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageNavigator(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeLocation.route,
        modifier = modifier
    ) {
        composable(route = HomeLocation.route) {
            HomeScreen(
                navigateToSearch = { navController.navigate(SearchLocation.route) },
            )
        }

        composable(route = SearchLocation.route) {
            SearchScreen(
                navigateToBookDescription = { navController.navigate(BookDescriptionLocation.route) },
                backToHome = { navController.navigate(HomeLocation.route) },
            )
        }

        composable(route = BookDescriptionLocation.route) {
            BookDescriptionPage(
                backToSearch = {
                    navController.navigate(SearchLocation.route)
                }
            )
        }
    }
}