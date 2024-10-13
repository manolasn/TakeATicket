package gr.manolasn.takeaticket.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import gr.manolasn.takeaticket.ui.composables.splashScreen.SplashScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {

    navigation (
        route = Graph.MAIN,
        startDestination = MainScreen.Start.route
    ) {

        composable(route = MainScreen.Start.route) {

            SplashScreen(
                mainScreen = {
                    navController.navigate(Graph.BOTTOM_NAV_GRAPH)
                }
            )

        }
    }
}


sealed class MainScreen(val route: String) {
    data object Start : MainScreen(route = "START")
}