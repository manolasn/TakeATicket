package gr.manolasn.takeaticket.domain.usecase

import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie
import gr.manolasn.takeaticket.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavoriteUC @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(movie: Movie): Flow<Resource<Boolean>> {
        return favoritesRepository.isMovieFavorite(movie)
    }
}