package com.example.filmlotto

import kotlinx.serialization.Serializable

@Serializable
data class TmdbResponse(
    val results: List<TmdbMovie>
)

@Serializable
data class TmdbMovie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val overview: String,
    val release_date: String,
    val vote_average: Float,

    var poster_full_url: String? = null
) {
    fun getPosterUrl(): String {
        return if (!poster_path.isNullOrEmpty()) {
            "https://image.tmdb.org/t/p/w500$poster_path"
        } else {
            "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg"
        }
    }
}
