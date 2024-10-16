package gr.manolasn.takeaticket.data.network

import gr.manolasn.takeaticket.common.utils.SortByMethod
import gr.manolasn.takeaticket.data.network.dto.getMovieDetails.GetMovieDetailsResponseDto
import gr.manolasn.takeaticket.data.network.dto.getMovies.GetMoviesResponseDto
import gr.manolasn.takeaticket.data.network.dto.searchMovie.GetMoviesSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCalls {

    @GET("discover/movie")
    suspend fun getMovies(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("sort_by") sortByMethod: String = SortByMethod.POPULARITY_DESC.value
    ): GetMoviesResponseDto


    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Header("Authorization") accessToken: String,
        @Path("id") id: String
    ): GetMovieDetailsResponseDto


    @GET("search/movie")
    suspend fun searchMovies(
        @Header("Authorization") accessToken: String,
        @Query("query") movieTitle: String,
        @Query("page") page: Int
    ): GetMoviesSearchResponseDto


}