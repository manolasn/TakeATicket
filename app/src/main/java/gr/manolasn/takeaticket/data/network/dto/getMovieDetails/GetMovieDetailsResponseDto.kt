package gr.manolasn.takeaticket.data.network.dto.getMovieDetails

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.common.utils.Transformations
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import java.util.Locale

data class GetMovieDetailsResponseDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val belongs_to_collection: Any?,
    val budget: Long?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val imdb_id: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val release_date: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

@RequiresApi(Build.VERSION_CODES.O)
fun GetMovieDetailsResponseDto.toDomain(): GetMovieDetails {
    return GetMovieDetails(
        id = id.toString(),
        movieName = title ?: "",
        imdbID = imdb_id ?: "",
        userScore = Transformations.formatDouble(vote_average ?: 0.0),
        releaseDate = Transformations.formatDateString(release_date ?: ""),
        shortDescription = overview ?: "",
        headline = tagline ?: "",
        budget = Transformations.formatBigNumberWithCommas(budget ?: 0),
        revenue = Transformations.formatBigNumberWithCommas(revenue ?: 0),
        duration = Transformations.transformMinutesToHoursAndMinutes(runtime ?: 0),
        language = Locale(original_language ?: "").displayLanguage,
        productionCompanies = production_companies?.map { it.toDomain() }?.ifEmpty { emptyList() }
            ?: emptyList(),
        imageURL = Transformations.backdropToFullImageURL(backdrop_path ?: ""),
        posterURL = Transformations.backdropToFullImageURL(poster_path ?: ""),
        genre = genres?.map { it.toDomain() } ?: emptyList()
    )
}