package com.pietroditta.weatherforecastapp.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar


const val SEARCH_SCREEN_RESULT_KEY = "SEARCH_SCREEN_RESULT_KEY"

@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack()
            })
    }) { innerPadding ->
        SearchContent(
            modifier = Modifier.padding(innerPadding),
            searchViewModel = searchViewModel,
            navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    modifier: Modifier,
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }
    val searchResults by searchViewModel.suggestions.collectAsState()

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    searchViewModel.onQueryChanged(it)
                    query = it
                },
                onSearch = {
                    searchViewModel.onSearch(it)
                    expanded = false
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = {
                    Text(text = "Search for a city or location")
                }
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        LazyColumn {
            items(searchResults.size) { index ->
                val result = searchResults[index]
                Text(
                    text = result.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            searchViewModel.setGeocoderIntoSavedStateHandle(
                                geocoderResult = result,
                                navController = navController
                            )
                            navController.popBackStack()
                            expanded = false
                        }
                )
            }
        }

    }
}
