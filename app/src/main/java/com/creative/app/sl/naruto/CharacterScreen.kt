package com.creative.app.sl.naruto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter

@Composable
fun CharacterScreen(modifier: Modifier = Modifier){
    val characterViewModel : MainViewModel = viewModel()
    val viewState by characterViewModel.charactersState
    Box(modifier = Modifier.fillMaxSize()) {
        when{
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(
                    text = "Error: ${viewState.error}",
                    modifier = Modifier.align(Alignment.Center) // Display actual error message
                )
            }
            else -> {
                NarutoScreen(naruto = viewState.list)
            }
        }

    }
}

@Composable
fun NarutoScreen(naruto : List<Naruto>){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(naruto){
            naruto ->
            NarutoItem(naruto = naruto)
        }
    }
}

@Composable
fun NarutoItem(naruto: Naruto){
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        if(naruto.images.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(naruto.images[0]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
        }
        Text(
            text = naruto.name,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}