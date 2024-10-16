package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal
import gr.manolasn.takeaticket.ui.theme.SetStatusBarColor
import gr.manolasn.takeaticket.ui.theme.Typography
import gr.manolasn.takeaticket.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title:String,
    onBackClick: () -> Unit={},
    topBarContainerColor: Color = AppGreyBlack,
    containerColor: Color = Color.White,
    textColor:Color= Color.White,
    action: @Composable (RowScope.() -> Unit) = {},
    hasNavigation:Boolean = true,
    isLoading: Boolean = false,
    toolbarContent: @Composable (paddingValues: PaddingValues) -> Unit
) {

    SetStatusBarColor(topBarContainerColor)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if (isLoading) White else containerColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .width(92.dp)
                                .height(12.dp)
                                .clip(CircleShape)
                                .shimmerEffect()
                        )
                    }
                    else {
                        Text(
                            text = title,
                            color = textColor,
                            style = Typography.titleMedium,
                        )
                    }
                },
                navigationIcon = {
                    if (!isLoading && hasNavigation) {
                        IconButton(onClick = { onBackClick() }) {
                            Image(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                colorFilter = ColorFilter.tint(AppTeal),
                                contentDescription = "BackArrow"
                            )
                        }
                    }
                },
                colors = topAppBarColors(
                    containerColor = if (isLoading) White else topBarContainerColor
                ),
                actions = action
            )
        }
    ){
        toolbarContent(it)
    }


}