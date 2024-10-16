package gr.manolasn.takeaticket.graphs.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gr.manolasn.takeaticket.graphs.Graph
import gr.manolasn.takeaticket.ui.composables.favorites.FavoritesScreen
import gr.manolasn.takeaticket.ui.composables.home.HomeScreen
import gr.manolasn.takeaticket.ui.composables.infoScreen.InfoScreen
import gr.manolasn.takeaticket.ui.composables.more.MoreScreen
import gr.manolasn.takeaticket.ui.composables.movieDetails.MovieDetailsScreen
import gr.manolasn.takeaticket.ui.composables.search.SearchScreen

@Composable
fun BottomNavigationGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = Graph.BOTTOM_NAV_GRAPH,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                title = BottomBarScreen.Home.titleResourceId,
                movieClicked = { navController.navigate(HomeScreen.MovieDetails.route + "/$it") },
                searchClicked = { navController.navigate(HomeScreen.SearchScreen.route) }
            )
        }

        composable(BottomBarScreen.Favorites.route) {
            FavoritesScreen(title = BottomBarScreen.Favorites.titleResourceId,
                favoriteItemClicked = { id -> navController.navigate(HomeScreen.MovieDetails.route + "/$id") })
        }

        composable(BottomBarScreen.More.route) {
            MoreScreen(title = BottomBarScreen.More.titleResourceId, goToInfo = { id ->
                navController.navigate(SettingsScreens.InfoScreen.route + "/$id")
            })
        }

        composable(HomeScreen.MovieDetails.route + "/{id}") {
            val id = it.arguments?.getString("id") ?: ""
            MovieDetailsScreen(id = id, onBackClicked = {
                navController.navigateUp()
            })
        }

        composable(SettingsScreens.InfoScreen.route + "/{id}") {
            val id = it.arguments?.getString("id") ?: ""
            InfoScreen(title = id.toInt(), goBack = { navController.navigateUp() })
        }

        composable(HomeScreen.SearchScreen.route) {
            SearchScreen(
                goBack = {
                    navController.navigateUp()
                },
                movieClicked = { navController.navigate(HomeScreen.MovieDetails.route + "/$it") }
            )
        }

    }

}

sealed class HomeScreen(val route: String) {
    data object MovieDetails : HomeScreen("MovieDetails")
    data object SearchScreen : HomeScreen("SearchScreen")
}

sealed class SettingsScreens(val route: String) {
    data object InfoScreen : SettingsScreens("Info")
}

