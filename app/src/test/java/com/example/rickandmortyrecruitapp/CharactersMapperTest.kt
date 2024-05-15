package com.example.rickandmortyrecruitapp

import com.example.rickandmortyrecruitapp.data.Character
import com.example.rickandmortyrecruitapp.data.CharacterWithEpisodes
import com.example.rickandmortyrecruitapp.data.CharactersMapper
import com.example.rickandmortyrecruitapp.data.Episode
import com.example.rickandmortyrecruitapp.data.EpisodeWithoutUrl
import com.example.rickandmortyrecruitapp.data.Location
import com.example.rickandmortyrecruitapp.data.Origin
import org.junit.Assert.*
import org.junit.Test

class CharactersMapperTest {
    @Test
    fun testMapToCharactersWithEpisodes() {
        val expectedResponse = listOf(
            CharacterWithEpisodes(0, "testName", "testStatus", "testSpecies", "testType", "testGender", Origin("testOrigin"), Location("testLocation"), "testImage",
                listOf(
                    EpisodeWithoutUrl("testName", "testAirDate", "S.2 E.2"),
                    EpisodeWithoutUrl("testName2", "testAirDate2", "S.10 E.10"),
                    )
            )
        )
        val testCharacter = Character(0, "testName", "testStatus", "testSpecies", "testType", "testGender", Origin("testOrigin"), Location("testLocation"), "testImage", listOf("testUrl", "testUrl2"))
        val testEpisode1 = Episode("testName", "testAirDate", "S02E02", "testUrl")
        val testEpisode2 = Episode("testName2", "testAirDate2", "S10E10", "testUrl2")


        val charactersMapper = CharactersMapper()
        val response = charactersMapper.mapToCharactersWithEpisodes(listOf(testCharacter), listOf(testEpisode1, testEpisode2))

        assertEquals(expectedResponse, response)
    }

    @Test
    fun testMapToCharactersWithEpisodes_WithOneEpisodeMatching() {
        val expectedResponse = listOf(
            CharacterWithEpisodes(0, "testName", "testStatus", "testSpecies", "testType", "testGender", Origin("testOrigin"), Location("testLocation"), "testImage",
                listOf(
                    EpisodeWithoutUrl("testName", "testAirDate", "S.2 E.2")
                )
            )
        )
        val testCharacter = Character(0, "testName", "testStatus", "testSpecies", "testType", "testGender", Origin("testOrigin"), Location("testLocation"), "testImage", listOf("testUrl"))
        val testEpisode1 = Episode("testName", "testAirDate", "S02E02", "testUrl")
        val testEpisode2 = Episode("testName2", "testAirDate2", "S10E10", "testUrl2")


        val charactersMapper = CharactersMapper()
        val response = charactersMapper.mapToCharactersWithEpisodes(listOf(testCharacter), listOf(testEpisode1, testEpisode2))

        assertEquals(expectedResponse, response)
    }

    @Test
    fun testMapToCharactersWithEpisodes_WithEpisodeNonParsable() {
        val expectedResponse = listOf(
            CharacterWithEpisodes(0, "testName", "testStatus", "testSpecies", "testType", "testGender", Origin("testOrigin"), Location("testLocation"), "testImage",
                listOf(
                    EpisodeWithoutUrl("testName", "testAirDate", "SE")
                )
            )
        )
        val testCharacter = Character(0, "testName", "testStatus", "testSpecies", "testType", "testGender", Origin("testOrigin"), Location("testLocation"), "testImage", listOf("testUrl"))
        val testEpisode1 = Episode("testName", "testAirDate", "SE", "testUrl")


        val charactersMapper = CharactersMapper()
        val response = charactersMapper.mapToCharactersWithEpisodes(listOf(testCharacter), listOf(testEpisode1))

        assertEquals(expectedResponse, response)
    }

}