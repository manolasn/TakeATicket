package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.manolasn.takeaticket.ui.theme.AppGrey
import gr.manolasn.takeaticket.ui.theme.Typography

@Composable
fun MoreScreenItem(
    text:Int,
    icon:Int,
    backgroundColor:Color = Color.White,
    hasArrow:Boolean = true,
    onClick: () -> Unit = {}
){
    Row (modifier = Modifier
        .fillMaxWidth(0.9f)
        .height(60.dp)
        .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
        .background(backgroundColor, shape = RoundedCornerShape(12.dp))
        .padding(start = 23.dp, end = 17.dp)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(radius = 0.dp, color = Color.White),
            onClick = {
                onClick()
            }
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Image(modifier = Modifier
            .fillMaxHeight(),
            painter = painterResource(id = icon),
            contentDescription ="my account icon"
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = stringResource(id = text),
            style = Typography.bodyMedium,
            fontSize = 14.sp,
            color = AppGrey
        )

        if(hasArrow) {

            Spacer(modifier = Modifier.weight(1f))

            Image(imageVector = Icons.Outlined.ArrowForward, contentDescription = "arrow icon" )
        }

    }

}