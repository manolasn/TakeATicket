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
import gr.manolasn.takeaticket.ui.theme.AppGrey

@Composable
fun LoadedView(
    loading:Boolean,
    content: @Composable () -> Unit
) {

    if (loading) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            CircularProgressIndicator(
                modifier = Modifier.size(size = 64.dp),
                color = AppGrey
            )

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(
                text = stringResource(id = R.string.please_wait),
                color = AppGrey
            )
        }
    }

    else content()

}