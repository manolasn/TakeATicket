package gr.manolasn.takeaticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gr.manolasn.takeaticket.graphs.RootNavigationGraph
import gr.manolasn.takeaticket.ui.theme.AppGrey
import gr.manolasn.takeaticket.ui.theme.TakeATicketTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TakeATicketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppGrey
                ) {
                    val navController = rememberNavController()
                    RootNavigationGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TakeATicketTheme {
        Greeting("Android")
    }
}