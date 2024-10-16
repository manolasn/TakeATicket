package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.manolasn.takeaticket.common.utils.NoRippleTheme
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal
import gr.manolasn.takeaticket.ui.theme.OrColor
import gr.manolasn.takeaticket.ui.theme.Typography
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun MovieDetailsTabButton(
    index: Int, isSelected: Boolean, text: String, onButtonClicked: (Int) -> Unit = {}
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

        Box(
            modifier = Modifier
                .padding(bottom = 1.dp)
                .fillMaxWidth()
                .background(color = if (isSelected) AppGreyBlack else White, shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
        ) {
            Button(modifier = Modifier.wrapContentSize(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ), onClick = {
                if (!isSelected) {
                    onButtonClicked(index)
                }
            }) {
                if (isSelected) {

                    Text(
                        text = text, style = Typography.bodyLarge, fontSize = 16.sp, color = AppTeal
                    )

                } else {
                    Text(
                        text = text,
                        style = Typography.bodySmall,
                        fontSize = 16.sp,
                        color = AppGreyBlack
                    )
                }

            }
        }


    }
}