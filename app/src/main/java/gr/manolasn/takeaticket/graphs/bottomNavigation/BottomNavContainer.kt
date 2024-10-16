package gr.manolasn.takeaticket.graphs.bottomNavigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gr.manolasn.takeaticket.common.utils.NoRippleTheme
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun BottomNavContainer(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(bottomBar = {
        BottomBar(navController)
    }) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            BottomNavigationGraph(navController = navController)
        }
    }

}

@Composable
fun BottomBar(
    navController: NavHostController
) {

    val screens = listOf(
        BottomBarScreen.Home, BottomBarScreen.Favorites, BottomBarScreen.More
    ).toMutableList()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestinationScreen = screens.any() { it.route == currentDestination?.route }

    if (bottomBarDestinationScreen) {
        NavigationBar(
            containerColor = AppGreyBlack, tonalElevation = 16.dp
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )

            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,

    ) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val interactionSource = remember { MutableInteractionSource() }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        NavigationBarItem(
            selected = isSelected,
            onClick = {
                navController.navigate(screen.route)
            },
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (isSelected) {
                        Icon(
                            painter = painterResource(id = screen.iconResourceIdSelected),
                            tint = AppTeal,
                            contentDescription = "Selected",
                            modifier = Modifier
                                .size(24.dp)
                        )

                        Text(
                            text = stringResource(id = screen.titleResourceId),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = AppTeal
                        )

                    } else {
                        Icon(
                            painter = painterResource(id = screen.iconResourceIdUnselected),
                            contentDescription = "Unselected",
                            tint = White,
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Text(
                            text = stringResource(id = screen.titleResourceId),
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            color = White
                        )
                    }
                }
            }, interactionSource = interactionSource, colors = NavigationBarItemDefaults.colors(
                indicatorColor = White, selectedIconColor = AppTeal
            )
        )
    }

}

