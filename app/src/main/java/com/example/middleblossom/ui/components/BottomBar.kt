package com.example.middleblossom.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.middleblossom.R
import com.example.middleblossom.ui.navigation.Destination
import com.example.middleblossom.ui.theme.Dimension

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(elevation = Dimension.Medium.size, backgroundColor = MaterialTheme.colors.background) {
        BottomNavigationItem(icon = { Icon(imageVector = Icons.Filled.Home, "") },
            label = { Text(text = stringResource(R.string.bottom_nav_home_tab)) },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Home.id } == true,
            onClick = {
                navController.navigate(Destination.Home.id) {
                    navController.graph.startDestinationRoute?.let { destination ->
                        popUpTo(destination) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        BottomNavigationItem(icon = { Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_entries), "") },
            label = { Text(text = stringResource(id = R.string.bottom_nav_calendar_tab)) },
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Entries.id } == true,
            onClick = {
                navController.navigate(Destination.Entries.id) {
                    navController.graph.startDestinationRoute?.let { destination ->
                        popUpTo(destination) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
    }
}