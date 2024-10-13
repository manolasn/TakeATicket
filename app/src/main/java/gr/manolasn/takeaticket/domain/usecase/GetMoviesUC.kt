package gr.manolasn.takeaticket.domain.usecase

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.GetMovies
import gr.manolasn.takeaticket.domain.model.movie.Movie
import gr.manolasn.takeaticket.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUC @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(page: Int): Flow<Resource<GetMovies>>{
        return homeRepository.getMovies(page)
    }
}