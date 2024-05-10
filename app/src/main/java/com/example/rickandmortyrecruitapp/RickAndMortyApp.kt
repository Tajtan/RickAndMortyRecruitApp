package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RickAndMortyApp(navController: NavHostController) {
    val charactersViewModel: MainViewModel = viewModel()
    val viewState by charactersViewModel.charactersState

    NavHost(navController = navController, startDestination = Screen.Characters.route) {
        composable(route = Screen.Characters.route) {
            CharactersScreen(viewState = viewState, navigateToDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("character", it)
                navController.navigate(Screen.Details.route)
            } )

        }
        composable(route = Screen.Details.route) {
            val character = navController.previousBackStackEntry?.savedStateHandle?.get<Character>("character") ?:
            Character("","","","","", Character.Origin(""), Character.Location(""), "", emptyList())
            DetailsScreen(character = character)
        }
    }
}