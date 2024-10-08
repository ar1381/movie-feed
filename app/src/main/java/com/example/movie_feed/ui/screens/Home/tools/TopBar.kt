package com.example.movie_feed.ui.screens.Home.tools

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip

@Composable
fun TopBar(navController: NavController, visibilty: Boolean?) {
    if (visibilty == true) {
        Box(contentAlignment = Alignment.CenterStart) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original/fR2TWD9dj8rQOUCkMl22xwuOHdd.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(com.example.movie_feed.R.string.description),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
            )
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                Text(
                    text = "Movie Feed",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Text(
                    text = "many movies for you to discover.",
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(navController)
            }
        }

    }
}
