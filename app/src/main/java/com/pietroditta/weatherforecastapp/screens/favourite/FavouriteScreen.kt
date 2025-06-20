package com.pietroditta.weatherforecastapp.screens.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.repository.MockedDataRepository
import com.pietroditta.weatherforecastapp.screens.search.SEARCH_SCREEN_RESULT_KEY
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar

@Composable
fun FavoriteScreen(navController: NavController, favoriteViewModel: FavoriteViewModel) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorites",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack() // Navigate back to the previous screen
            })
    }) { innerPadding ->

        val favorites by favoriteViewModel.favorites.collectAsState()

        FavoriteContent(
            modifier = Modifier.padding(innerPadding), favorites = favorites,
            onItemClicked = {
                favoriteViewModel.setFavoriteIntoSavedStateHandle(
                    favorite = it,
                    navController = navController
                )
                navController.popBackStack()
            },
            onDeleteClicked = {
                favoriteViewModel.delete(it)
            })
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier,
    favorites: List<Favorite> = emptyList(),
    onItemClicked: (Favorite) -> Unit = {},
    onDeleteClicked: (Favorite) -> Unit = {},
) {

    if (favorites.isEmpty()) {
        Text(
            text = "No favorites added yet.", modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(favorites.size) { index ->
            val favorite = favorites[index]
            FavoriteItem(
                favorite = favorite,
                onDeleteClicked = {
                    onDeleteClicked(it)
                },
                onItemClicked = {
                    onItemClicked(it)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteItem(
    favorite: Favorite = MockedDataRepository.getFavorites().first(),
    onItemClicked: (Favorite) -> Unit = {},
    onDeleteClicked: (Favorite) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onItemClicked(favorite) },
        shape = CircleShape,
        color = Color.Red.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = favorite.name,
            )

            RoundedText(
                text = favorite.country,
                color = Color.Cyan
            )

            IconButton(
                onClick = {
                    onDeleteClicked(favorite)
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Favorite",
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
fun RoundedText(
    text: String,
    color: Color = Color.Black
) {
    Surface(
        shape = CircleShape,
        color = color,
        modifier = Modifier
            .padding(4.dp)
            .size(24.dp, 24.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
            )
        }
    }
}
