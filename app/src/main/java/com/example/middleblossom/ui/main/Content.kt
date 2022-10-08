package com.example.middleblossom.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.middleblossom.ui.components.BottomBar
import com.example.middleblossom.ui.components.DefaultSnackBar
import com.example.middleblossom.ui.components.Toolbar
import com.example.middleblossom.ui.navigation.NavBarsHelper
import com.example.middleblossom.ui.navigation.SetupNavGraph
import com.example.middleblossom.ui.theme.ComposePlaygroundTheme

@Composable
fun Content() = ComposePlaygroundTheme(darkTheme = isSystemInDarkTheme()) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val isUserLoggedIn = remember { mutableStateOf(false) }
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { DefaultSnackBar(it) },
        topBar = {
            if (NavBarsHelper.shouldDisplayToolbar(backstackEntry.value)) {
                Toolbar(navController, backstackEntry, isUserLoggedIn)
            }
        },
        bottomBar = {
            if (NavBarsHelper.shouldDisplayBottomBar(backstackEntry.value)) {
                BottomBar(navController)
            }
        }) { paddingValues ->
        SetupNavGraph(navController = navController, paddingValues, scaffoldState, isUserLoggedIn)
    }
}
