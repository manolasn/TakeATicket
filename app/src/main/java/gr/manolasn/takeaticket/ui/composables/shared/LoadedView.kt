package gr.manolasn.takeaticket.ui.composables.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack

@Composable
fun LoadedView(
    loading:Boolean,
    error: Boolean = false,
    content: @Composable () -> Unit
) {

    if (loading || error) {

        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            CircularProgressIndicator(
                modifier = Modifier.size(size = 64.dp),
                color = AppGreyBlack
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(
                text = stringResource(id = R.string.please_wait),
                color = AppGreyBlack
            )
        }
    }

    else content()

}