package com.example.rickandmortyrecruitapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CharactersScreen(
    viewState: MainViewModel.CharactersState
) {
    Text(text = viewState.list.toString())
    Text(text = viewState.error.toString())
}