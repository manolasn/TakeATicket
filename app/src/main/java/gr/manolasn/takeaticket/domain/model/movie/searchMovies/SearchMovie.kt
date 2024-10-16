package gr.manolasn.takeaticket.domain.model.movie.searchMovies

import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie

data class SearchMovie(
    val id: String = String(),
    val movieName: String = String(),
    val rating: String = String(),
    val releaseDate: String = String(),
    val shortDescription: String = String(),
    val imageURL: String = String(),
    var isFavorite: Boolean = false
)

fun SearchMovie.toMovie(): Movie {
    return Movie(
        id = id,
        movieName = movieName,
        rating = rating,
        releaseDate = releaseDate,
        shortDescription = shortDescription,
        imageURL = imageURL,
        isFavorite = isFavorite
    )
}
