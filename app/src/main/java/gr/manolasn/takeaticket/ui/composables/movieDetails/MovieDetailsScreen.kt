package gr.manolasn.takeaticket.ui.composables.movieDetails

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.common.utils.NoRippleTheme
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.toMovie
import gr.manolasn.takeaticket.ui.composables.movieDetails.tabs.DescriptionTab
import gr.manolasn.takeaticket.ui.composables.movieDetails.tabs.InformationTab
import gr.manolasn.takeaticket.ui.composables.shared.LoadedView
import gr.manolasn.takeaticket.ui.composables.shared.MovieDetailsTabButton
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal

@OptIn(
    ExperimentalGlideComposeApi::class
)
@Composable
fun MovieDetailsScreen(
    id: String,
    onBackClicked: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    val movieDetails by viewModel.movieDetails.collectAsState()
    val isMovieFavorite by viewModel.isMovieFavorite.collectAsState()
    val loading by viewModel.loading.collectAsState()


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // you can use the ActivityResult(it) here
        }

    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(id)
    }

    val buttonList: List<String> = listOf(
        stringResource(id = R.string.description),
        stringResource(id = R.string.information),
    )


    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {


        TopBar(title = movieDetails.movieName, onBackClick = {
            onBackClicked()
        }, action = {
            IconButton(
                onClick = {
                    if (isMovieFavorite) {
                        viewModel.removeFavorite(movieDetails.toMovie())
                    } else {
                        viewModel.addFavorite(movieDetails.toMovie())
                    }
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.favorites),
                    contentDescription = "fav ic",
                    colorFilter = ColorFilter.tint(
                        color = if (isMovieFavorite) AppTeal
                        else Color.White
                    )
                )
            }

            IconButton(onClick = {
                launcher.launch(viewModel.shareClicked())
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "share ic"
                )
            }
        }) { paddingValues ->
            LoadedView(loading) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        model = movieDetails.imageURL,
                        contentDescription = "movieImage",
                        contentScale = ContentScale.Fit,
                    )


                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(modifier = Modifier
                        .height(40.dp)
                        .drawBehind {
                            drawLine(
                                color = AppGreyBlack,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 6f
                            )
                        }
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) {
                        items(buttonList.size) { index ->
                            Spacer(modifier = Modifier.width(4.dp))
                            MovieDetailsTabButton(index = index,
                                isSelected = viewModel.buttonsBoolList.value[index],
                                text = buttonList[index],
                                onButtonClicked = {
                                    viewModel.tabClicked(it)
                                })
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Description Tab
                    if (viewModel.buttonsBoolList.value[0]) {
                        DescriptionTab(movieDetails)
                    }
                    //Information Tab
                    else if (viewModel.buttonsBoolList.value[1]) {
                        InformationTab(movieDetails)
                    }
                }


            }

        }
    }
}



