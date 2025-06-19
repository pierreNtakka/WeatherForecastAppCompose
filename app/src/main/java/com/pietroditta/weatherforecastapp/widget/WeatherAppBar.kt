package com.pietroditta.weatherforecastapp.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
                    navController?.navigate(WeatherScreen.SettingsScreen.name)
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Settings Icon",
                        tint = Color.White
                    )
                }
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