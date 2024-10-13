package gr.manolasn.takeaticket.data.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gr.manolasn.takeaticket.domain.model.movie.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritesDatabase? = null

        fun getDatabase(context: Context): FavoritesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoritesDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}