package com.example.movie_feed.ui.screens.view_all.tools

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movie_feed.R

@Composable
fun ErrorView(networkError:Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_anim))
    if (networkError){
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