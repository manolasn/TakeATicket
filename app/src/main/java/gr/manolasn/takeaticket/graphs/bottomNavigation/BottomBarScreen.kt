package gr.manolasn.takeaticket.graphs.bottomNavigation

import gr.manolasn.takeaticket.R


sealed class BottomBarScreen(
    val route : String,
    val titleResourceId : Int,
    val iconResourceIdUnselected : Int,
    val iconResourceIdSelected : Int
) {
    data object Home : BottomBarScreen(
        route = "Home",
        titleResourceId = R.string.bottomNav_home,
        iconResourceIdUnselected =  R.drawable.home ,
        iconResourceIdSelected = R.drawable.home
    )

    data object Favorites : BottomBarScreen(
        route = "Favorites",
        titleResourceId = R.string.bottomNav_favorites,
        iconResourceIdUnselected = R.drawable.favorites,
        iconResourceIdSelected = R.drawable.favorites
    )

    data object More : BottomBarScreen(
        route = "More",
        titleResourceId = R.string.bottomNav_more,
        iconResourceIdUnselected = R.drawable.more,
        iconResourceIdSelected = R.drawable.more
    )
}