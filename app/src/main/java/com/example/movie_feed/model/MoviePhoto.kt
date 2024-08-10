package com.example.movie_feed.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePhoto(
    val id: Int,
    @SerialName(value = "img_src")
    val imgSrc: String,
    val title: String,
    val overview: String,
    val release_date: String
)