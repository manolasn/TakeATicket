package gr.manolasn.takeaticket.domain.model.movie.getmoviedetails

import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie

data class GetMovieDetails(
    val id: String = String(),
    val movieName: String = String(),
    val userScore: String = String(),
    val releaseDate: String = String(),
    val shortDescription: String = String(),
    val headline: String = String(),
    val budget: String = String(),
    val revenue: String = String(),
    val duration: String = String(),
    val language: String = String(),
    val productionCompanies: List<Company> = emptyList(),
    val imageURL: String = String(),
    val imdbID: String = String(),
    val genre: List<String> = emptyList(),
    var isFavorite: Boolean = false
)

fun GetMovieDetails.toMovie(): Movie {
    return Movie(
        id = id,
        movieName = movieName,
        rating = userScore,
        releaseDate = releaseDate,
        shortDescription = shortDescription,
        imageURL = imageURL,
        isFavorite = isFavorite
    )
}
