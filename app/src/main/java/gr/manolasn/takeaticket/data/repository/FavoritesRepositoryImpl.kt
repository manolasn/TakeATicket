package gr.manolasn.takeaticket.data.repository

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.data.localDatabase.FavoritesDao
import gr.manolasn.takeaticket.domain.model.movie.getmovies.GetMovies
import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie
import gr.manolasn.takeaticket.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository {
    override fun getFavorites(): Flow<Resource<List<Movie>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val favorites = favoritesDao.getFavoriteMovies()
                emit(Resource.Success(favorites))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured", e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    override fun addFavorite(movie: Movie): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                favoritesDao.insert(movie.copy(isFavorite = true))
                emit(Resource.Success(true))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured", e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach database!", 0))
            }
        }
    }

    override fun removeFavorite(movie: Movie): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                favoritesDao.deleteMovie(movie.id)
                emit(Resource.Success(true))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured", e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach database!", 0))
            }
        }
    }

    override fun isMovieFavorite(movie: Movie): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                val isFavorite = favoritesDao.isMovieFavorite(movie.id) > 0
                emit(Resource.Success(isFavorite))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured", e.code()
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach database!", 0))
            }
        }
    }

}