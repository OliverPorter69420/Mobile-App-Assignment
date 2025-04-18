package com.example.dissertation_app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmationDialog(
    itemName: String,
    onConfirmDelete: () -> Unit,
    onDismissRequest: () -> Unit
) {
    BasicAlertDialog(
        modifier = Modifier.padding(10.dp),
        onDismissRequest = onDismissRequest,
        content = {
            Card(
                Modifier.size(200.dp)
                    .border(3.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                        .fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(0.5f),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Confirm Delete",
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(10.dp)
                        )

                        Text(
                            text = "Are you sure you want to delete:"
                        )

                        Text(
                            text = itemName,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(5.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.padding(10.dp)
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                onConfirmDelete()
                                onDismissRequest()
                            }
                        ) {
                            Text(
                                text = "Delete"
                            )
                        }

                        Button(
                            onClick = onDismissRequest
                        ) {
                            Text(
                                text = "Cancel"
                            )
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BookTopAppBarPreview() {
    BookTopAppBar(
        title = "Page's title",
        canNavigateBack = true
    )
}

@Preview
@Composable
fun DeleteConfirmationDialogPreview() {
    DeleteConfirmationDialog(
        itemName = "item name",
        onConfirmDelete = {},
        onDismissRequest = {}
    )
}








