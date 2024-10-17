package gr.manolasn.takeaticket.domain.repository

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.common.utils.SortByMethod
import gr.manolasn.takeaticket.domain.model.movie.getmovies.GetMovies
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import gr.manolasn.takeaticket.domain.model.movie.searchMovies.GetSearchMovies
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getMovies(page: Int, shortByMethod: SortByMethod): Flow<Resource<GetMovies>>
    fun getMovieDetails(id: String): Flow<Resource<GetMovieDetails>>
    fun searchMovies(title: String): Flow<Resource<GetSearchMovies>>

}