package gr.manolasn.takeaticket.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.manolasn.takeaticket.domain.model.movie.Movie

interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: String)

    @Query("SELECT isFavorite FROM movies WHERE id = :movieId")
    suspend fun isMovieFavorite(movieId: String): Boolean
}