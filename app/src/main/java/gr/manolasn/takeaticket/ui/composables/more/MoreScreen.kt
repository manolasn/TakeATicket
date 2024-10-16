package gr.manolasn.takeaticket.ui.composables.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gr.manolasn.takeaticket.BuildConfig
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.ui.composables.shared.MoreScreenItem
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.Typography

@Composable
fun MoreScreen(
    title : Int,
    goToInfo: (String) -> Unit ,

) {

    val uriHandler = LocalUriHandler.current

    TopBar(
        title = stringResource(id = title),
        hasNavigation = false,
        topBarContainerColor = AppGreyBlack,
        textColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState())
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            MoreScreenItem(text = R.string.terms_of_use, icon = R.drawable.terms_of_use, onClick = {
                goToInfo(R.string.terms_of_use.toString())
            })

            Spacer(modifier = Modifier.height(13.dp))

            MoreScreenItem(text = R.string.privacy_policy,
                icon = R.drawable.privacy_policy,
                onClick = {
                    goToInfo(R.string.privacy_policy.toString())
                })

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "v.${BuildConfig.VERSION_NAME}", style = Typography.labelMedium, color = AppGreyBlack
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Made by", style = Typography.labelSmall, color = AppGreyBlack
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Manolas Nikiforos©",
                        style = Typography.labelSmall,
                        color = AppGreyBlack
                    )
                    Image(
                        modifier = Modifier.clickable {
                                uriHandler.openUri("https://www.linkedin.com/in/nikiforos-manolas/")
                            }.padding(start = 8.dp),
                        painter = painterResource(id = R.drawable.external_link),
                        contentDescription = "externallink"
                    )
                }

            }

        }
    }

}