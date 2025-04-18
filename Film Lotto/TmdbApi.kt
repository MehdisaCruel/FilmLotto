package com.example.filmlotto

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun getUpcomingMovies(): TmdbResponse? {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    return try {
        val url = "https://api.themoviedb.org/3/discover/movie" +
                "?include_adult=false&include_video=false&language=fa-IR&page=1&sort_by=popularity.desc"

        val result: TmdbResponse = client.get(url) {
            headers {
                append("accept", "application/json")
                append(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MTZiNDIwYjQzNjliYzg2NzM3YzI1YjkyNTcyY2YyNyIsIm5iZiI6MTc0NDI3NjE1MS42Niwic3ViIjoiNjdmNzhhYjdkM2FiN2Q3YThiYWQzZTExIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.BlVrphd7BiJ8-QxkI1y19mexxfziThR6LJAf4Wq3cyk"
                )
            }
        }.body()

        result.results.forEach { movie ->
            val posterUrl = if (movie.poster_path != null) {
                "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            } else {
                "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg"
            }

            Log.d("Movie Poster URL", posterUrl)
        }

        client.close()
        result
    } catch (e: Exception) {
        Log.e("APIError", "Failed to fetch popular movies: ${e.message}", e)
        client.close()
        null
    }
}
