package gr.manolasn.takeaticket.ui.composables.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.domain.model.movie.Movie
import gr.manolasn.takeaticket.domain.model.movie.Pagination
import gr.manolasn.takeaticket.domain.usecase.GetMoviesUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUC: GetMoviesUC
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    var loading = _loading.asStateFlow()

    private val _loadingNewItems = MutableStateFlow(false)
    var loadingNewItems = _loadingNewItems.asStateFlow()

    private var _movies = MutableStateFlow(listOf<Movie>())
    val movies = _movies.asStateFlow()


    private val _page = MutableStateFlow(1)
    var page = _page.asStateFlow()

    private val _pageCount = MutableStateFlow(0)
    var pageCount = _pageCount.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    var currentPage = _currentPage.asStateFlow()


    fun getMoviesWithPagination() {
        _movies.value = mutableListOf()
        _page.value = 1
        _loading.update { true }
        getPage()
    }

    private fun getPage() {

        getMoviesUC(_page.value).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    if (page.value == 1) _loading.update { true }
                    else _loadingNewItems.update { true }
                }

                is Resource.Success -> {
                    result.data.let { response ->

                        if (page.value == 1) {
                            _movies.value = response?.movies ?: mutableListOf()
                            _loading.update { false }
                        } else {
                            _movies.update { currentPropertiesOnList ->
                                val newPropertiesItems = mutableListOf<Movie>()
                                newPropertiesItems.addAll(currentPropertiesOnList)
                                newPropertiesItems.addAll(response?.movies!!)
                                newPropertiesItems
                            }
                            _loadingNewItems.update { false }

                        }
                        updatePaginationInfo(result.data?.pagination)
                        _page.value++
                    }
                }

                is Resource.Error -> {
                    _movies.value = mutableListOf()
                    if (page.value == 1) _loading.update { false }
                    else _loadingNewItems.update { false }
                }

            }
        }.launchIn(viewModelScope)

    }

    private fun updatePaginationInfo(pagination: Pagination?) {
        pagination?.let {

            _pageCount.value = it.totalPages
            _currentPage.value = it.currentPage

        }
    }


    fun loadMore() {
        if (_currentPage.value < _pageCount.value) {
            getPage()
        }
    }

}