package gr.manolasn.takeaticket.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.App
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.common.utils.SortByMethod
import gr.manolasn.takeaticket.data.network.ApiCalls
import gr.manolasn.takeaticket.data.network.dto.getMovieDetails.toDomain
import gr.manolasn.takeaticket.data.network.dto.getMovies.toDomain
import gr.manolasn.takeaticket.data.network.dto.searchMovie.toDomain
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import gr.manolasn.takeaticket.domain.model.movie.getmovies.GetMovies
import gr.manolasn.takeaticket.domain.model.movie.searchMovies.GetSearchMovies
import gr.manolasn.takeaticket.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiCalls: ApiCalls
) : HomeRepository {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun getMovies(page: Int, shortByMethod: SortByMethod): Flow<Resource<GetMovies>> {
        return flow {
            try {
                emit(Resource.Loading())
                val callResponse =
                    apiCalls.getMovies(
                        accessToken = "Bearer ${App.accessToken}",
                        page = page,
                        sortByMethod = shortByMethod.value
                    ).toDomain()
                emit(Resource.Success(callResponse))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured",
                        e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getMovieDetails(id: String): Flow<Resource<GetMovieDetails>> {
        return flow {
            try {
                emit(Resource.Loading())
                val callResponse =
                    apiCalls.getMovieDetails(accessToken = "Bearer ${App.accessToken}", id = id)
                        .toDomain()
                emit(Resource.Success(callResponse))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured",
                        e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun searchMovies(title: String): Flow<Resource<GetSearchMovies>> {
        return flow {
            try {
                emit(Resource.Loading())
                val callResponse =
                    apiCalls.searchMovies(
                        accessToken = "Bearer ${App.accessToken}",
                        movieTitle = title,
                        page = 1
                    ).toDomain()
                emit(Resource.Success(callResponse))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured",
                        e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }


}