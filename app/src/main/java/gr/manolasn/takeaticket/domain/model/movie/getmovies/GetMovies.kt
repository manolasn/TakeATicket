package gr.manolasn.takeaticket.domain.model.movie.getmovies

data class GetMovies(
    val movies: List<Movie>,
    val pagination: Pagination
)
