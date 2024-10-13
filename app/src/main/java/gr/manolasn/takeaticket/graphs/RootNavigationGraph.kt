package gr.manolasn.takeaticket.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gr.manolasn.takeaticket.graphs.bottomNavigation.BottomNavContainer


@Composable
fun RootNavigationGraph (navController: NavHostController,
){
    //we pass the navController from main activity
    NavHost(
    navController = navController,
    startDestination = Graph.MAIN,
    route = Graph.ROOT) {
        //we pass the navController only to our authentication navigation graph

        mainNavGraph(
            navController = navController)

        composable(route = Graph.BOTTOM_NAV_GRAPH) {
            BottomNavContainer()
        }

    }
}


//this contains the routes of every navigation graph we have in our application
object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val BOTTOM_NAV_GRAPH = "bottom_nav_graph"
}