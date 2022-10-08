package com.example.middleblossom.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateTo(destination: Destination, cleanBackStack: Boolean = false) {
    val navOptions = NavOptions
        .Builder()
        .setPopUpTo(graph.startDestinationId, cleanBackStack)
        .build()
    navigate(destination.id, navOptions)
}

