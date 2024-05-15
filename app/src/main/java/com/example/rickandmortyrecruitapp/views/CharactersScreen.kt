package com.example.rickandmortyrecruitapp.views

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
import androidx.compose.material3.Button
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyrecruitapp.MainViewModel
import com.example.rickandmortyrecruitapp.R
import com.example.rickandmortyrecruitapp.data.CharacterWithEpisodes

// Starting destination screen, which based on viewState (representing the state of list of characters being loaded) displays:
// - Circular Progress Indicator when loading data,
// - Error message with retry button when an error occurs (with on Retry Clicked parameter used for retry button),
// - Loaded Characters Screen Composable when loading is finished and there is no error. It passes down list of characters to display and navigate to details function.
@Composable
fun CharactersScreen(
    viewState: MainViewModel.CharactersState,
    navigateToDetails: (CharacterWithEpisodes) -> Unit,
    onRetryClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator()
            }
            viewState.error != null -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = "Error loading characters.\n\nCheck Your Internet connection.", textAlign = TextAlign.Center)
                    Button(onClick = { onRetryClicked() }) {
                        Text(text = "Retry", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            else -> {
                LoadedCharactersScreen(characters = viewState.list, navigateToDetails)
            }
        }
    }
}

// Screen to display list of characters. It contains a scaffold with top bar and a lazy column with id of a CharacterWithEpisodes as key for performance.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadedCharactersScreen(
    characters: List<CharacterWithEpisodes>,
    navigateToDetails: (CharacterWithEpisodes) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
            items(characters, key = { it.id } ) {
                    character ->
                CharacterItem(character = character, navigateToDetails)
            }
        }
    }
}

// Composable function representing singular item in the list of characters. It displays character's image, name, and status. It takes two parameters:
// - the character to display,
// - a function to navigate to the details screen when the item is clicked (whole row is clickable).
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
                    .size(107.dp)
                    .padding(8.dp)
                    .border(
                        BorderStroke(3.dp, statusColor(status = character.status)),
                        CircleShape
                    )
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = character.name, style = MaterialTheme.typography.titleLarge)
                Text(text = character.status, style = MaterialTheme.typography.bodySmall)
            }
        }
    }