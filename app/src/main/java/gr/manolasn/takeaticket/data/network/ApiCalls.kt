package gr.manolasn.takeaticket.data.network

import gr.manolasn.takeaticket.data.network.dto.getMovies.GetMoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiCalls {

    @GET("discover/movie")
    suspend fun getMovies(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int
    ): GetMoviesResponseDto


}