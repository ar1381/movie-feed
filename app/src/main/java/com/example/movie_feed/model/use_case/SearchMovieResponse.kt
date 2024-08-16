package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieItem
import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)