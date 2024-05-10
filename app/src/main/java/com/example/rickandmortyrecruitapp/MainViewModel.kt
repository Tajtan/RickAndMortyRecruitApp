package com.example.rickandmortyrecruitapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _charactersState = mutableStateOf(CharactersState())
    val charactersState: State<CharactersState> = _charactersState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(){
        viewModelScope.launch {
            try {
                val allCharacters: MutableList<Character> = mutableListOf()
                var currentPage = 1
                var totalPages = 1

                while (currentPage <= totalPages) {
                    val response = characterService.getCharacters(currentPage)
                    totalPages = response.info.pages

                    allCharacters.addAll(response.results)

                    currentPage++
                }

                _charactersState.value = _charactersState.value.copy(
                    list = allCharacters,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _charactersState.value = _charactersState.value.copy(
                    loading = false,
                    error = "Error fetching Characters: ${e.message}"
                )
            }
        }
    }

    data class CharactersState(
        val loading: Boolean = true,
        val list: List<Character> = emptyList(),
        val error: String? = null
    )
}