package com.pietroditta.weatherforecastapp.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.BuildConfig
import com.pietroditta.weatherforecastapp.R
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack()
            })
    }) { innerPadding ->
        AboutContent(modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun AboutContent(modifier: Modifier = Modifier) {
    Surface(modifier = modifier, color = Color.White) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${stringResource(R.string.app_name)}\n" +
                        "Version ${BuildConfig.VERSION_NAME}\n" +
                        "Developed by Pietro Ditta",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Weather API:\n ${BuildConfig.API_BASE_URL}\n",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,

                )
        }
    }
}
