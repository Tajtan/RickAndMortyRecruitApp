package com.example.rickandmortyrecruitapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CharactersScreen(
    viewState: MainViewModel.CharactersState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            viewState.error != null -> {
                Text(text = viewState.error)
            }
            else -> {
                LoadedCharactersScreen(characters = viewState.list)
            }
        }
    }
}

@Composable
fun LoadedCharactersScreen(
    characters: List<Character>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(characters) {
            character ->
            CharacterItem(character = character)
        }
    }
}

@Composable
fun CharacterItem(
    character: Character
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(

        ) {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = "Character image",
                modifier = Modifier
                    .size(90.dp)
                    .aspectRatio(1f)
            )
            Column(

            ) {
                Text(text = character.name)
                Text(text = character.status)
            }
        }
    }

}