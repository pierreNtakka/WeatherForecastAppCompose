package com.pietroditta.weatherforecastapp.screens.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar

@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack() // Navigate back to the previous screen
            })
    }) { innerPadding ->
        SettingsContent(
            modifier = Modifier.padding(innerPadding), settingsViewModel = settingsViewModel
        )
    }
}


@Composable
fun SettingsContent(modifier: Modifier = Modifier, settingsViewModel: SettingsViewModel) {

    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb = settingsViewModel.units.collectAsState().value

    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0]
    else choiceFromDb[0].unitName

    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Change Units of Measurement", modifier = Modifier.padding(bottom = 15.dp)
            )

            IconToggleButton(
                checked = !unitToggleState,
                onCheckedChange = {
                    unitToggleState = !it
                    choiceState = if (unitToggleState) {
                        "Imperial (F)"
                    } else {
                        "Metric (C)"
                    }
                    Log.d("TAG", "MainContent: $unitToggleState")

                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clip(shape = RectangleShape)
                    .padding(5.dp)
                    .background(Color.Magenta.copy(alpha = 0.4f))
            ) {

                Text(text = if (unitToggleState) "Fahrenheit ºF" else "Celsius ºC")


            }
            Button(
                onClick = {
                    settingsViewModel.saveMeasurementUnit(choiceState)
                },
                modifier = Modifier
                    .padding(3.dp)
                    .align(CenterHorizontally),
                shape = RoundedCornerShape(34.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFFEFBE42)
                )
            ) {
                Text(
                    text = "Save",
                    modifier = Modifier.padding(4.dp),
                    color = Color.White,
                    fontSize = 17.sp
                )

            }

        }
    }

}
