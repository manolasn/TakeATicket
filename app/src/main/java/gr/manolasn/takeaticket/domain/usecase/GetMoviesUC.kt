package gr.manolasn.takeaticket.domain.usecase

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.common.utils.SortByMethod
import gr.manolasn.takeaticket.domain.model.movie.getmovies.GetMovies
import gr.manolasn.takeaticket.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUC @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(page: Int, shortByMethod: SortByMethod): Flow<Resource<GetMovies>>{
        return homeRepository.getMovies(page,shortByMethod)
    }
}