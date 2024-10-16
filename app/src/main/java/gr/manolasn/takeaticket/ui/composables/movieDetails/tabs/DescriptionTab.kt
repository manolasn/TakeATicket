package gr.manolasn.takeaticket.ui.composables.movieDetails.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal
import gr.manolasn.takeaticket.ui.theme.TermsOfUseColor
import gr.manolasn.takeaticket.ui.theme.Typography
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun DescriptionTab(
    movieDetails : GetMovieDetails
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.wrapContentWidth(),
            text = movieDetails.headline,
            style = Typography.displaySmall,
            color = TermsOfUseColor
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.overview),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = movieDetails.shortDescription,
            style = Typography.bodySmall,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = AppGreyBlack
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.language),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = movieDetails.language,
            style = Typography.bodySmall,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = AppGreyBlack
        )


        Spacer(modifier = Modifier.height(16.dp))


        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.genre),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        LazyRow(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(movieDetails.genre.size) { item ->
                Box(
                    modifier = Modifier
                        .background(
                            color = AppGreyBlack,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(4.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = movieDetails.genre[item],
                        style = Typography.bodySmall,
                        fontSize = 14.sp,
                        color = AppTeal,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.user_score),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = AppGreyBlack, shape = RoundedCornerShape(32.dp)
                )
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(32.dp),
                    color = AppTeal
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.wrapContentWidth(),
                    text = movieDetails.userScore,
                    style = Typography.bodyMedium,
                    fontSize = 20.sp,
                    color = White
                )
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "%",
                    style = Typography.bodySmall,
                    fontSize = 8.sp,
                    color = White
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}