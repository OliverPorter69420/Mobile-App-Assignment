package com.example.dissertation_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dissertation_app.ui.navigation.PageNavigator

@Composable
fun BookApp(navController: NavHostController = rememberNavController()) {
    PageNavigator(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    iconButtonFunctional: Boolean = false,
    buttonFunctionality: () -> Unit = {},
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    iconDescription: String = "back",
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (canNavigateBack) {
            IconButton(
                onClick = navigateUp
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
        } else {
            Spacer(Modifier.size(48.dp))
        }

        CenterAlignedTopAppBar(

            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                )
            },
            modifier = Modifier.weight(1f),
            scrollBehavior = scrollBehavior,
        )

        if (iconButtonFunctional) {
            IconButton(
                onClick = buttonFunctionality
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription
                )
            }
        } else {
            Spacer(Modifier.size(48.dp))
        }
    }
}







