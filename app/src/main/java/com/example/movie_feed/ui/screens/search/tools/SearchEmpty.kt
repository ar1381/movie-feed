package com.example.movie_feed.ui.screens.search.tools

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.movie_feed.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants

@Composable
fun SearchEmpty(empty:Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_lottie))
    if (empty){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(100.dp)
            )
        }
    }
}