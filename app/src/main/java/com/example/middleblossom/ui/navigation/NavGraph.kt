package com.example.middleblossom.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.middleblossom.ui.screens.doctors.AllDoctorsScreen
import com.example.middleblossom.ui.screens.entries.EntriesScreen
import com.example.middleblossom.ui.screens.exercise.ExerciseScreen
import com.example.middleblossom.ui.screens.home.HomeScreen
import com.example.middleblossom.ui.screens.login.LoginScreen
import com.example.middleblossom.ui.screens.profile.ProfileScreen
import com.example.middleblossom.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    scaffoldState: ScaffoldState,
    isUserLoggedIn: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.id,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Destination.Splash.id) { SplashScreen(navController) }
        composable(route = Destination.Home.id) { HomeScreen(navController, scaffoldState,isUserLoggedIn) }
        composable(route = Destination.Exercise.id) { ExerciseScreen(navController) }
        composable(route = Destination.Login.id) { LoginScreen(navController, scaffoldState,isUserLoggedIn) }
        composable(route = Destination.Entries.id) { EntriesScreen() }
        composable(route = Destination.AllDoctors.id) { AllDoctorsScreen() }
        composable(route = Destination.Profile.id) { ProfileScreen()}
    }
}

//https://developer.android.com/jetpack/compose/libraries#viewmodel
//you should access and call ViewModel instances at screen-level composables, that is,
// close to a root composable called from an activity, fragment, or destination of a Navigation graph.
//You should never pass down ViewModel instances to other composables,
// pass only the data they need and functions that perform the required logic as parameters.