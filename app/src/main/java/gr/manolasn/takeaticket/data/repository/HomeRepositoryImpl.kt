package gr.manolasn.takeaticket.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import gr.manolasn.takeaticket.App
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.data.network.ApiCalls
import gr.manolasn.takeaticket.data.network.dto.getMovies.toDomain
import gr.manolasn.takeaticket.domain.model.movie.GetMovies
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
    override fun getMovies(page: Int): Flow<Resource<GetMovies>> {
        return flow {
            try {
                emit(Resource.Loading())
                val callResponse =
                    apiCalls.getMovies(accessToken = "Bearer ${App.accessToken}", page = page).toDomain()
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