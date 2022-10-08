package com.example.middleblossom.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.middleblossom.R
import com.example.middleblossom.ui.navigation.Destination
import com.example.middleblossom.ui.navigation.NavBarsHelper
import com.example.middleblossom.ui.navigation.navigateTo
import com.example.middleblossom.ui.theme.AlmostBlack

@Composable
fun Toolbar(
    navController: NavController,
    backstackEntry: State<NavBackStackEntry?>,
    isUserLoggedIn: MutableState<Boolean>
) = TopAppBar(
    backgroundColor = if (isSystemInDarkTheme()) AlmostBlack else MaterialTheme.colors.background,
    navigationIcon = {
        if (NavBarsHelper.shouldDisplayUpButton(backstackEntry.value)) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    },
    title = { Text(text = "") },
    actions = {
        if (NavBarsHelper.shouldDisplayProfileIcon(backstackEntry.value)) {
            IconButton(onClick = {
                if (isUserLoggedIn.value)
                    navController.navigateTo(Destination.Profile)
                else
                    navController.navigateTo(Destination.Login)
            }) {
                if (isUserLoggedIn.value) {
                    Image(
                        ImageVector.vectorResource(id = R.drawable.ic_profile),
                        contentDescription = ""
                    )

                } else {
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )

                }
            }
        }
    }
)
