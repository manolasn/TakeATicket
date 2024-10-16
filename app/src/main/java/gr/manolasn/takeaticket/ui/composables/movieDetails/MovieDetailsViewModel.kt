package gr.manolasn.takeaticket.ui.composables.movieDetails

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.common.ErrorState
import gr.manolasn.takeaticket.common.Resource
import gr.manolasn.takeaticket.common.utils.Transformations
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.toMovie
import gr.manolasn.takeaticket.domain.model.movie.getmovies.Movie
import gr.manolasn.takeaticket.domain.usecase.AddFavoriteUC
import gr.manolasn.takeaticket.domain.usecase.GetMovieDetailsUC
import gr.manolasn.takeaticket.domain.usecase.IsFavoriteUC
import gr.manolasn.takeaticket.domain.usecase.RemoveFavoriteUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUC: GetMovieDetailsUC,
    private val addFavoriteUC: AddFavoriteUC,
    private val isFavoriteUC: IsFavoriteUC,
    private val removeFavoriteUC: RemoveFavoriteUC,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    var loading = _loading.asStateFlow()

    private val _movieDetails = MutableStateFlow(GetMovieDetails())
    var movieDetails = _movieDetails.asStateFlow()

    private val _isMovieFavorite = MutableStateFlow(false)
    var isMovieFavorite = _isMovieFavorite.asStateFlow()

    var buttonsBoolList = mutableStateOf(listOf(true, false))

    private var errorState by mutableStateOf(ErrorState())


    fun getMovieDetails(id: String) {
        getMovieDetailsUC(id).onEach { result ->
            when (result) {

                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    result.data.let { res ->
                        _movieDetails.value = res!!
                        isMovieFavorite(_movieDetails.value.toMovie())
                        _loading.value = false
                    }
                }

                is Resource.Error -> {
                    errorState = ErrorState("Failed", result.statusCode ?: 0)
                    _loading.value = false
                }

            }
        }.launchIn(viewModelScope)
    }


    fun addFavorite(movie: Movie) {
        addFavoriteUC(movie).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    result.data.let {
                        _loading.value = false
                        _isMovieFavorite.value = true
                    }
                }

                is Resource.Error -> {
                    errorState = ErrorState("Failed", result.statusCode ?: 0)
                    _loading.value = false
                }
            }


        }.launchIn(viewModelScope)
    }

    fun removeFavorite(movie: Movie) {
        removeFavoriteUC(movie).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    result.data.let {
                        _loading.value = false
                        _isMovieFavorite.value = false
                    }
                }

                is Resource.Error -> {
                    errorState = ErrorState("Failed", result.statusCode ?: 0)
                    _loading.value = false
                }
            }


        }.launchIn(viewModelScope)
    }

    private fun isMovieFavorite(movie: Movie) {
        isFavoriteUC(movie).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    result.data.let {
                        _loading.value = false
                        _isMovieFavorite.value = it!!
                        _movieDetails.update {
                            _movieDetails.value.copy(isFavorite = true)
                        }
                    }
                }

                is Resource.Error -> {
                    errorState = ErrorState("Failed", result.statusCode ?: 0)
                    _loading.value = false
                }
            }

        }.launchIn(viewModelScope)
    }


    fun shareClicked(): Intent {
        val dataToShare = Transformations.idToIMDB(movieDetails.value.imdbID)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            val subject = context.resources.getString(R.string.shared_link_subject)
            putExtra(Intent.EXTRA_SUBJECT, subject);

            // Adding the text to share using putExtra
            putExtra(Intent.EXTRA_TEXT, dataToShare)
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, null)
    }

    fun tabClicked(index: Int) {
        buttonsBoolList.value = List(buttonsBoolList.value.size) { i ->
            i == index
        }
    }


}