package gr.manolasn.takeaticket.data.localDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: String)

    @Query("SELECT COUNT(*) FROM movies WHERE id = :movieId") // Use COUNT to check existence
    suspend fun isMovieFavorite(movieId: String): Int // Return count
}