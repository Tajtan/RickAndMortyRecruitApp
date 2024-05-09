package com.example.rickandmortyrecruitapp

sealed class Screen(val route: String) {
    object Characters: Screen("charactersscreen")
    object Details: Screen("detailsscreen ")
}