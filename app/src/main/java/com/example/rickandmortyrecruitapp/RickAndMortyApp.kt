package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortyrecruitapp.views.CharactersScreen
import com.example.rickandmortyrecruitapp.views.DetailsScreen
import com.example.rickandmortyrecruitapp.views.Screen

// Composable function defining how navigation in app works and creating instance of MainViewModel with charactersState.
@Composable
fun RickAndMortyApp(navController: NavHostController) {
    val charactersViewModel: MainViewModel = viewModel()
    val viewState by charactersViewModel.charactersState

    NavHost(navController = navController, startDestination = Screen.Characters.route) {
        composable(route = Screen.Characters.route) {
            CharactersScreen(
                viewState = viewState,
                navigateToDetails = {
                    charactersViewModel.pickCharacter(it)
                    navController.navigate(Screen.Details.route)
                },
                onRetryClicked = { charactersViewModel.retryFetchCharacters() }
            )
        }
        composable(route = Screen.Details.route) {
            viewState.displayedCharacter?.let { character ->
                DetailsScreen(
                    character = character,
                    onBackNavClicked = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}