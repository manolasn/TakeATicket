package gr.manolasn.takeaticket.domain.repository

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<Resource<List<Movie>>>
    fun addFavorite(movie: Movie): Flow<Resource<Boolean>>
    fun removeFavorite(movie: Movie): Flow<Resource<Boolean>>
    fun isMovieFavorite(movie: Movie): Flow<Resource<Boolean>>
}