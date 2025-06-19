package com.pietroditta.weatherforecastapp.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.navigation.WeatherScreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun WeatherAppBar(
    title: String = "Weather Forecast",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController? = null,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {

    var topBarDropDownMenuState by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Magenta,
        ),
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    navController?.navigate(WeatherScreen.SearchScreen.name)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    topBarDropDownMenuState = !topBarDropDownMenuState
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More Icon",
                        tint = Color.White
                    )
                }

                TopBarDropdownMenu(
                    navController = navController!!,
                    expanded = topBarDropDownMenuState,
                    onDismissRequest = {
                        topBarDropDownMenuState = false
                    })


            }
        },
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = onButtonClicked) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        })

}

@Composable
fun TopBarDropdownMenu(
    navController: NavController,
    expanded: Boolean,
    onDismissRequest: () -> Unit = { },
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {

        DropdownMenuItem(
            text = { Text("Favorite") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = Color.Black
                )
            },
            onClick = {
                navController.navigate(WeatherScreen.FavoritesScreen.name)
            }
        )
        DropdownMenuItem(
            text = { Text("About") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info Icon",
                    tint = Color.Black
                )
            },
            onClick = {
                navController.navigate(WeatherScreen.AboutScreen.name)
            }
        )
        DropdownMenuItem(
            text = { Text("Settings") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings Icon",
                    tint = Color.Black
                )
            },
            onClick = { navController.navigate(WeatherScreen.SettingsScreen.name) }
        )
    }
}