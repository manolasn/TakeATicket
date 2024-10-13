package gr.manolasn.takeaticket.domain.model.movie

data class GetMovies(
    val movies: List<Movie>,
    val pagination: Pagination
)
