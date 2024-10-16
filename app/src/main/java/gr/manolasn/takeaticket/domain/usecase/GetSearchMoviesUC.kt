package gr.manolasn.takeaticket.domain.usecase

import GetSearchMovies
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchMoviesUC @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(title: String): Flow<Resource<GetSearchMovies>> {
        return homeRepository.searchMovies(title)
    }
}