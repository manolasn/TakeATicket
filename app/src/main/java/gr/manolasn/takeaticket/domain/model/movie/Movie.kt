package gr.manolasn.takeaticket.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: String = String(),
    val movieName: String = String(),
    val rating: String = String(),
    val releaseDate: String = String(),
    val shortDescription: String = String(),
    val imageURL: String = String(),
    var isFavorite:Boolean = false
)
