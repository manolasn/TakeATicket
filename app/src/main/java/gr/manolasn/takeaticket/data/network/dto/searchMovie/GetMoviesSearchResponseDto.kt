package gr.manolasn.takeaticket.data.network.dto.searchMovie

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.domain.model.movie.searchMovies.GetSearchMovies

data class GetMoviesSearchResponseDto(
    val page: Int = 0,
    val results: List<Result>,
    val total_pages: Int = 0,
    val total_results: Int = 0
)

@RequiresApi(Build.VERSION_CODES.O)
fun GetMoviesSearchResponseDto.toDomain(): GetSearchMovies {
    return GetSearchMovies(
        searchMovies = results.map { it.toDomain() }
    )
}