package gr.manolasn.takeaticket.ui.composables.favorites

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie
import gr.manolasn.takeaticket.domain.usecase.GetFavoritesUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUC: GetFavoritesUC
) : ViewModel() {
    private val _loading = MutableStateFlow(true)
    var loading = _loading.asStateFlow()

    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())
    var favorites = _favorites.asStateFlow()



    fun getFavorites() {

        getFavoritesUC().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    result.data.let { response ->
                        _favorites.value = response!!
                        _loading.value = false
                    }
                }

                is Resource.Error -> {
                    _loading.value = false
                }

            }
        }.launchIn(viewModelScope)

    }


}
