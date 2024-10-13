package gr.manolasn.takeaticket.domain.repository

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.GetMovies
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getMovies(page: Int): Flow<Resource<GetMovies>>

}