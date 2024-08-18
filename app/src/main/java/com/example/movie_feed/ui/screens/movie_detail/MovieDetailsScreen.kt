package com.example.movie_feed.ui.screens.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie_feed.R
import com.example.movie_feed.data.database.MovieDatabase
import com.example.movie_feed.data.database.MovieEntity
import com.example.movie_feed.model.tools.formattedYear
import com.example.movie_feed.model.tools.minuteToTime
import com.example.movie_feed.model.use_case.GetVideosResponse
import com.example.movie_feed.model.use_case.MovieCreditsResponse
import com.example.movie_feed.model.use_case.MovieDetailsResponse
import com.example.movie_feed.ui.screens.Screen
import com.example.movie_feed.ui.screens.movie_detail.tools.CircularProgress
import com.example.movie_feed.ui.screens.movie_detail.tools.ItemCastCard
import com.example.movie_feed.ui.screens.view_all.tools.ErrorView
import com.example.movie_feed.ui.screens.view_all.tools.IsLoading
import com.example.movie_feed.ui.screens.view_all.tools.ToolBar
import java.util.*

@Composable
fun MovieDetailsScreen(
    navController: NavController, title: String, viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
//    val movieDao = MovieDatabase.getDatabase(context).movieDao()
    val bookmarked = remember { mutableStateOf(false) }
    Scaffold(topBar = {
        ToolBar(title = title, onBack = {
            navController.popBackStack()
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            val details = viewModel.movieDetailsResponse.value
            val cast = viewModel.movieCreditsResponse.value
            val videos = viewModel.getVideosResponse.value
            if (details.id != null && cast.id != null) {
                LazyColumn(content = {
                    item { ItemPoster(details) }
                    item { ItemTitle(navController,details, videos, bookmarked, onBookmarkClick = {
                        viewModel.bookmarkMovie(
                            MovieEntity(
                                id = details.id,
                                title = details.title.toString(),
                                bookmarked = !bookmarked.value
                            )
                        )
                        bookmarked.value = !bookmarked.value
                    }
                    ) }
                    item { ItemOverview(details) }
                    item { ItemCast(cast) }
                })
            }
            IsLoading(isLoading = viewModel.isLoading.containsValue(true))
            ErrorView(viewModel.apiError.value)
        }
    }

}


@Composable
fun ItemPoster(response: MovieDetailsResponse) {
    Box(modifier = Modifier.padding(horizontal = 15.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original" + response.backdropPath).crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 60.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original" + response.posterPath).crossfade(true).build(),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .align(Alignment.CenterStart)
                .clip(shape = RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun ItemTitle(navController: NavController, response: MovieDetailsResponse, videos: GetVideosResponse, bookmarked: MutableState<Boolean>,
              onBookmarkClick: () -> Unit) {

    Spacer(modifier = Modifier.height(20.dp))

    val title = response.title ?: ""
    val year = formattedYear(response.releaseDate) ?: ""
    Text(
        text = buildAnnotatedString {
            append(title); append(" ");withStyle(style = SpanStyle(color = Color.Gray)) {
            append(
                "($year)"
            )
        }
        },
        style = MaterialTheme.typography.h5,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "R",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            modifier = Modifier.padding(end = 10.dp)
        )

        val originalLanguage = if (response.originalLanguage != null) {
            " (${response.originalLanguage.uppercase(Locale.ROOT)})"
        } else ""
        Text(
            text = response.releaseDate + originalLanguage + " " + response.runtime?.let {
                minuteToTime(
                    it
                )
            },
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            modifier = Modifier.padding(end = 10.dp)
        )
        Icon(
            imageVector = if (bookmarked.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = stringResource(id = R.string.description),
            tint = Color.Black,
            modifier = Modifier
                .size(18.dp)
                .clickable(onClick = onBookmarkClick)
        )
    }


    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        content = {
            response.genres?.forEach {
                item {
                    Text(
                        text = if (it == response.genres.last()) it?.name.toString() else it?.name + ", ",
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
            }
        })

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgress((response.voteAverage?.toFloat()?.div(10)) ?: 0f)

        Text(
            text = "User Score",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp, end = 15.dp)
        )

        Divider(
            modifier = Modifier
                .height(20.dp)
                .width(3.dp)
                .background(Color.LightGray)
        )

        Row(
            modifier = Modifier
                .clickable(onClick = {
                    val item = videos.results?.last { it?.type == "Trailer" }
                    navController.navigate(Screen.YoutubePlayerScreen.route + "youtubeCode=${item?.key}")
                })
                .padding(start = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.description),
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Play Trailer",
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                modifier = Modifier.padding(end = 10.dp)
            )

        }
    }


}

@Composable
fun ItemOverview(response: MovieDetailsResponse) {
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Overview",
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        modifier = Modifier.padding(start = 15.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    val lineHeight = MaterialTheme.typography.h6.fontSize * 4 / 3
    Text(
        text = response.overview ?: "",
        style = MaterialTheme.typography.body1,
        lineHeight = lineHeight,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}

@Composable
fun ItemCast(credits: MovieCreditsResponse) {
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Top Billed Cast",
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(content = {
        credits.cast?.forEach {
            item {
                ItemCastCard(it)
            }
        }
    })
    Spacer(modifier = Modifier.height(15.dp))
}


