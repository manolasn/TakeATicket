package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.common.utils.Constants
import gr.manolasn.takeaticket.common.utils.NoRippleTheme
import gr.manolasn.takeaticket.domain.model.movie.Movie
import gr.manolasn.takeaticket.ui.theme.AppGrey
import gr.manolasn.takeaticket.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LazyColumnItem(
    movie: Movie,
    isFavoriteEnabled: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {

    Spacer(modifier = Modifier.height(16.dp))

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(160.dp)
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(9.dp))
                .background(Color.White, shape = RoundedCornerShape(9.dp))
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = false, radius = 10.dp, color = Color.White
                    ),
                    onClick = {
                        onClick()
                    }),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                contentAlignment = Alignment.Center
            ) {

                if (movie.imageURL.isNotEmpty()) {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(10.dp)),
                        model = movie.imageURL.ifEmpty { Constants.IMAGE_NOT_FOUNT },
                        contentDescription = "store Image",
                        contentScale = ContentScale.Crop,
                    )
                }


                if (isFavoriteEnabled) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.93f)
                            .fillMaxWidth(0.93f),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Image(
                            modifier = Modifier.clickable {
                                onFavoriteClick()
                            }, painter = painterResource(
                                id = if (movie.isFavorite) R.drawable.favorite_icon
                                else R.drawable.not_favorite_icon
                            ), contentDescription = "favorite icon"
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = movie.movieName,
                        style = Typography.bodyLarge,
                        color = AppGrey,
                        fontSize = 18.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(Modifier.height(8.dp))

                RowImageWithText(
                    image = R.drawable.star,
                    text = movie.rating,
                    spaceBetween = 8.dp,
                    maxLines = 1,
                    textFontSize = 14.sp
                )
                Spacer(Modifier.height(4.dp))
                RowImageWithText(
                    image = R.drawable.calendar,
                    text = movie.releaseDate,
                    maxLines = 1,
                    spaceBetween = 8.dp,
                    textFontSize = 14.sp
                )
                Spacer(Modifier.height(4.dp))
                RowImageWithText(
                    image = R.drawable.info,
                    text = movie.shortDescription,
                    maxLines = 3,
                    spaceBetween = 8.dp,
                    textFontSize = 14.sp
                )


            }
        }
    }

    Spacer(modifier = Modifier.height(4.dp))


}