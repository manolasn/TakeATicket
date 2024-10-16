package gr.manolasn.takeaticket.data.network.dto.getMovieDetails

import gr.manolasn.takeaticket.common.utils.Transformations
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.Company

data class ProductionCompany(
    val id: Int?,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)

fun ProductionCompany.toDomain(): Company {
    return Company(
        name = name ?: "",
        imageURL = Transformations.backdropToImageURL(logo_path ?: ""),
        country = origin_country ?: ""

    )
}