package gr.manolasn.takeaticket.graphs.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gr.manolasn.takeaticket.graphs.Graph
import gr.manolasn.takeaticket.ui.composables.favorites.FavoritesScreen
import gr.manolasn.takeaticket.ui.composables.home.HomeScreen
import gr.manolasn.takeaticket.ui.composables.more.MoreScreen

@Composable
fun BottomNavigationGraph (
    navController: NavHostController
){

    NavHost(
        navController = navController,
        route = Graph.BOTTOM_NAV_GRAPH,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route){
            HomeScreen(
                title = BottomBarScreen.Home.titleResourceId,
                movieClicked = {
                    navController.navigate(HomeScreen.MovieDetails.route + "/$it")
                }
//                propertyClicked = { id -> navController.navigate(HomeScreen.PropertyDetails.route + "/$id") }

            )
        }

        composable(BottomBarScreen.Favorites.route){
            FavoritesScreen(
                title = BottomBarScreen.Favorites.titleResourceId,
                favoriteItemClicked = { id -> navController.navigate(HomeScreen.MovieDetails.route + "/$id")},
                goToHomePage = {navController.navigate(BottomBarScreen.Home.route)}
            )
        }

        composable(BottomBarScreen.More.route){
            MoreScreen(
                title = BottomBarScreen.More.titleResourceId,
                goToInfo = { id ->
                    navController.navigate(SettingsScreens.InfoScreen.route + "/$id")
                },
                goToHomePage = {
                    navController.navigate(BottomBarScreen.Home.route)
                }
            )
        }

    }

}

sealed class HomeScreen(val route: String) {
    data object MovieDetails : HomeScreen("MovieDetails")
}

sealed class SettingsScreens(val route: String) {
    data object InfoScreen : SettingsScreens("Info")
}

