package gr.manolasn.takeaticket.data.network.dto.searchMovie

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.common.utils.Transformations
import gr.manolasn.takeaticket.domain.model.movie.searchMovies.SearchMovie

data class Result(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String,
    val release_date: String?,
    val title: String?,
    val video: Boolean,
    val vote_average: Double?,
    val vote_count: Int
)

@RequiresApi(Build.VERSION_CODES.O)
fun Result.toDomain() : SearchMovie{
    return SearchMovie(
        id = id.toString(),
        movieName = title?:"",
        shortDescription = overview?:"",
        rating = Transformations.formatDouble(vote_average ?: 0.0),
        releaseDate = Transformations.formatDateString(release_date?:"",),
        imageURL = Transformations.backdropToImageURL(backdrop_path?:"",),
        isFavorite = false
    )
}