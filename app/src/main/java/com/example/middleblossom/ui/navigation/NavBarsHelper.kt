package com.example.middleblossom.ui.navigation

import androidx.navigation.NavBackStackEntry

object NavBarsHelper {
    fun shouldDisplayUpButton(backStackEntry: NavBackStackEntry?): Boolean {
        return !(backStackEntry?.destination?.route == Destination.Home.id ||
                backStackEntry?.destination?.route == Destination.Entries.id)
    }

    fun shouldDisplayToolbar(backStackEntry: NavBackStackEntry?): Boolean {
        return backStackEntry?.destination?.route != Destination.Splash.id
    }

    fun shouldDisplayBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        return !(backStackEntry?.destination?.route == Destination.Exercise.id ||
                backStackEntry?.destination?.route == Destination.Splash.id ||
                backStackEntry?.destination?.route == Destination.Login.id ||
                backStackEntry?.destination?.route == Destination.AllDoctors.id ||
                backStackEntry?.destination?.route == Destination.Profile.id
                )
    }

    fun shouldDisplayProfileIcon(backStackEntry: NavBackStackEntry?): Boolean {
        return !(backStackEntry?.destination?.route == Destination.Login.id
                || backStackEntry?.destination?.route == Destination.AllDoctors.id
                || backStackEntry?.destination?.route == Destination.Profile.id
                )
    }
}
