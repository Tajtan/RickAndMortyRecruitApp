package com.example.rickandmortyrecruitapp

sealed class Screen(val route: String) {
    data object Characters: Screen("charactersscreen")
    data object Details: Screen("detailsscreen ")
}