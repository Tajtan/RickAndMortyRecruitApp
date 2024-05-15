package com.example.rickandmortyrecruitapp.networking

import com.example.rickandmortyrecruitapp.data.Character
import com.example.rickandmortyrecruitapp.data.Episode

class Networking {
    // Function called by fetchCharactersAndEpisodes to fetch characters from all pages in the API using characterService and ApiService interface.
    suspend fun fetchAllCharacters(): List<Character> {
        val allCharacters: MutableList<Character> = mutableListOf()
        var currentPage = 1
        var totalPages = 1

        while (currentPage <= totalPages) {
            val response = characterService.getCharacters(currentPage)
            totalPages = response.info.pages

            allCharacters.addAll(response.results)

            currentPage++
        }

        return allCharacters
    }

    // Function called by fetchCharactersAndEpisodes to fetch episodes from all pages in the API using characterService and ApiService interface.
    suspend fun fetchAllEpisodes(): List<Episode> {
        val allEpisodes: MutableList<Episode> = mutableListOf()
        var currentPage = 1
        var totalPages = 1

        while (currentPage <= totalPages) {
            val response = characterService.getEpisodes(currentPage)
            totalPages = response.info.pages

            allEpisodes.addAll(response.results)

            currentPage++
        }

        return allEpisodes
    }
}