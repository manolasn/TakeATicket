package gr.manolasn.takeaticket.ui.composables.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.searchMovies.SearchMovie
import gr.manolasn.takeaticket.domain.usecase.GetSearchMoviesUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchMoviesUC: GetSearchMoviesUC
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()


    private var _searchResults = MutableStateFlow(listOf<SearchMovie>())
    val searchResults = _searchResults.asStateFlow()


     fun searchMovies(title: String) {
        searchMoviesUC(title).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                  _loading.value = true
                }

                is Resource.Success -> {
                    result.data.let { response ->
                        if (response != null) {
                            _searchResults.value = response.searchMovies
                        }
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