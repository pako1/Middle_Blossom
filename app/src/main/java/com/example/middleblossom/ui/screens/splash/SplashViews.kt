package com.example.middleblossom.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.middleblossom.R
import com.example.middleblossom.ui.navigation.Destination
import com.example.middleblossom.ui.navigation.navigateTo
import com.example.middleblossom.ui.utils.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashView(isVisible: Boolean,navController: NavController, isVisibleCallBack : (Boolean)->Unit){
    LaunchedEffect(key1 = isVisible) {
        delay(Constants.SPLASH_VISIBILITY_IN_MS)
        isVisibleCallBack.invoke(false)
        delay(Constants.SPLASH_INVISIBILITY_IN_MS)
        navController.navigateTo(Destination.Home, true)
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(Constants.SPLASH_ANIMATION_IN_MS)),
        exit = fadeOut(animationSpec = tween(Constants.SPLASH_ANIMATION_IN_MS))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_undraw_fitness_tracker_3033),
                contentDescription = null
            )
        }
    }
}