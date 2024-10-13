package gr.manolasn.takeaticket.data.network.dto.getMovies

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.domain.model.movie.GetMovies
import gr.manolasn.takeaticket.domain.model.movie.Pagination

data class GetMoviesResponseDto(
    val page: Int = 0,
    val results: List<Result>,
    val total_pages: Int = 0,
    val total_results: Int = 0
)


@RequiresApi(Build.VERSION_CODES.O)
fun GetMoviesResponseDto.toDomain(): GetMovies {
    return GetMovies(
        movies = results.map { it.toDomain() }, pagination = Pagination(
            currentPage = page, totalPages = total_pages
        )
    )
}




