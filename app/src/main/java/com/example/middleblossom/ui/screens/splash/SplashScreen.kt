package com.example.middleblossom.ui.screens.splash

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.middleblossom.BuildConfig
import com.example.middleblossom.ui.navigation.Destination
import com.example.middleblossom.ui.navigation.navigateTo

@Composable
fun SplashScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }
    if (BuildConfig.DEBUG) {
        navController.navigateTo(Destination.Home, true)
    }
    SplashView(isVisible = isVisible, navController = navController){
        isVisible = false
    }
}