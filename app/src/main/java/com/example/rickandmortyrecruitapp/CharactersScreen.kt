package com.example.rickandmortyrecruitapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CharactersScreen(
    viewState: MainViewModel.CharactersState,
    navigateToDetails: (CharacterWithEpisodes) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(

                )
            }
            viewState.error != null -> {
                Text(text = "Error loading characters.\nCheck Your Internet connection.", textAlign = TextAlign.Center)
            }
            else -> {
                LoadedCharactersScreen(characters = viewState.list, navigateToDetails)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadedCharactersScreen(
    characters: List<CharacterWithEpisodes>,
    navigateToDetails: (CharacterWithEpisodes) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.main_title_top_bar))
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(characters, key = { it.id }) {
                    character ->
                CharacterItem(character = character, navigateToDetails)
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterWithEpisodes,
    navigateToDetails: (CharacterWithEpisodes) -> Unit
) {
        Row(
            modifier = Modifier
                .clickable {
                    navigateToDetails(character)
                }
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = "Character image",
                modifier = Modifier
                    .size(96.dp)
                    .padding(8.dp)
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        CircleShape
                    )
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = character.status, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }