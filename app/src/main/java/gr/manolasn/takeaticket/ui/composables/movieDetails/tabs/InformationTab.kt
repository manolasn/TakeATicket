package gr.manolasn.takeaticket.ui.composables.movieDetails.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.common.utils.Constants
import gr.manolasn.takeaticket.domain.model.movie.getmoviedetails.GetMovieDetails
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun InformationTab(
    movieDetails: GetMovieDetails
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
            text = stringResource(R.string.duration),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = movieDetails.duration,
            style = Typography.bodySmall,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = AppGreyBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.realease_date),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = movieDetails.releaseDate,
            style = Typography.bodySmall,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = AppGreyBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.budget),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = movieDetails.budget,
            style = Typography.bodySmall,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = AppGreyBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.revenue),
            style = Typography.bodyMedium,
            fontSize = 20.sp,
            color = AppGreyBlack
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = movieDetails.revenue,
            style = Typography.bodySmall,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = AppGreyBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.production_companies),
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
            items(movieDetails.productionCompanies.size) { item ->

                GlideImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    model = movieDetails.productionCompanies[item].imageURL.ifEmpty { Constants.IMAGE_NOT_FOUNT },
                    contentDescription = "Production company image",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(16.dp))

            }

        }


        Spacer(modifier = Modifier.height(16.dp))

    }
}