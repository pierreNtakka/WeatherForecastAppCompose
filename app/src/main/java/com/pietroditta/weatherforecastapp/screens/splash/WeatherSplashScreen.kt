package com.pietroditta.weatherforecastapp.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.R
import com.pietroditta.weatherforecastapp.navigation.WeatherScreen

@Composable
fun WeatherSplashScreen(navController: NavController) {

    val scale = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = androidx.compose.animation.core.tween(durationMillis = 800, easing = {
                OvershootInterpolator(9f).getInterpolation(it)
            })
        )

        // Navigate to the main screen after the animation
        navController.navigate(WeatherScreen.MainScreen.name) {
            // Clear the back stack to prevent returning to the splash screen
            popUpTo(WeatherScreen.SplashScreen.name) { inclusive = true }
        }
    }

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(95.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Black)
            )

            Text(
                "Find your weather forecast here!",
                color = Color.LightGray,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WeatherSplashScreenPreview() {
    WeatherSplashScreen(navController = NavController(context = LocalContext.current))
}