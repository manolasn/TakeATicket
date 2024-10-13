package gr.manolasn.takeaticket.ui.composables.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.domain.model.movie.Movie
import gr.manolasn.takeaticket.ui.composables.shared.LazyColumnItem
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.theme.AppGrey
import gr.manolasn.takeaticket.ui.theme.Typography
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun FavoritesScreen (
    title: Int,
    favoriteItemClicked: (String) -> Unit,
    goToHomePage: () -> Unit = {}
){

    val favoritesList = emptyList<Movie>()

    TopBar(
        title = stringResource(id = title),
        hasNavigation = false,
        topBarContainerColor = AppGrey,
        textColor = White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (favoritesList.isEmpty()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 75.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(id = R.drawable.logo_init_screen),
                    contentDescription = "settings background",
                    contentScale = ContentScale.Fit,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.75f),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.no_favorites),
                    style = Typography.bodySmall,
                    fontSize = 18.sp,
                    color = Color.Black
                )

            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(favoritesList.size) { index ->
                        LazyColumnItem(
                            movie = favoritesList[index],
                            onClick = { favoriteItemClicked(favoritesList[index].id) },
                            onFavoriteClick = {
                                //make call with id viewModel.favorites[index].id
                            }
                        )
                    }
                }
            }
        }

    }
}