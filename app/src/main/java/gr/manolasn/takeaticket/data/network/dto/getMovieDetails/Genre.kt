package gr.manolasn.takeaticket.data.network.dto.getMovieDetails

data class Genre(
    val id: Int,
    val name: String
)

fun Genre.toDomain(): String {
    return name
}