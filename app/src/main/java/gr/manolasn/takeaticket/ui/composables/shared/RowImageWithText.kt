package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.Typography

@Composable
fun RowImageWithText(
    image: Int,
    text: String,
    textTitle: String,
    textTitleFontSize: TextUnit,
    spaceBetween: Dp,
    maxLines: Int,
    isUserScore: Boolean,
    textFontSize: TextUnit,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.wrapContentHeight(),
            text = textTitle,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = Typography.displaySmall,
            fontSize = textTitleFontSize,
            color = AppGreyBlack,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement
        ) {
            Image(
                modifier = Modifier.size(14.dp),
                painter = painterResource(id = image),
                contentDescription = text
            )
            Spacer(modifier = Modifier.width(spaceBetween))
            Text(
                modifier = Modifier.wrapContentHeight(),
                text = if (isUserScore) "$text %" else text,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                style = Typography.bodySmall,
                fontSize = textFontSize,
                color = AppGreyBlack,
            )

        }
    }
}
