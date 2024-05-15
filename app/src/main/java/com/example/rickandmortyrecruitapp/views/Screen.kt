package com.example.rickandmortyrecruitapp.views

// Data class containing Screen route values for code maintenance and readability.
sealed class Screen(val route: String) {
    data object Characters: Screen("charactersscreen")
    data object Details: Screen("detailsscreen ")
}