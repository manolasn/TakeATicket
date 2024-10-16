package gr.manolasn.takeaticket.domain.usecase

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import gr.manolasn.takeaticket.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUC @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(id: String): Flow<Resource<GetMovieDetails>> {
        return homeRepository.getMovieDetails(id)
    }

}