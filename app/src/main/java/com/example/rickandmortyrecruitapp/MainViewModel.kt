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
                val response = characterService.getCharacters()
                _charactersState.value = _charactersState.value.copy(
                    list = response.results,
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