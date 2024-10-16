package gr.manolasn.takeaticket.ui.composables.infoScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.common.utils.Constants.PRIVACY_POLICY
import gr.manolasn.takeaticket.common.utils.Constants.TERMS_OF_USE
import gr.manolasn.takeaticket.ui.composables.shared.HTMLView
import gr.manolasn.takeaticket.ui.composables.shared.LoadedView
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun InfoScreen(
    infoScreenViewModel: InfoScreenViewModel = hiltViewModel(),
    title : Int,
    goBack: () -> Unit ,
) {
    
    val loading by infoScreenViewModel.loading.collectAsState(true)

    LaunchedEffect(Unit) {
        when (title) {
            R.string.terms_of_use -> infoScreenViewModel.getInfo(TERMS_OF_USE)
            R.string.privacy_policy -> infoScreenViewModel.getInfo(PRIVACY_POLICY)
        }
    }

    TopBar(
        title = stringResource(id = title),
        topBarContainerColor = AppGreyBlack,
        textColor = White,
        onBackClick = {
            goBack()
        }
    ) {
        LoadedView(loading){
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 25.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top){

                HTMLView(infoScreenViewModel.currentInfo.description)

                Spacer(modifier = Modifier.padding(bottom = 25.dp))

            }
        }
    }
}