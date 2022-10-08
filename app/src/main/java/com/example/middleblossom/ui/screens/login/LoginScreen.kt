package com.example.middleblossom.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch


//https://developer.android.com/jetpack/compose/libraries#viewmodel
//you should access and call ViewModel instances at screen-level composables, that is,
// close to a root composable called from an activity, fragment, or destination of a Navigation graph.
//You should never pass down ViewModel instances to other composables,
// pass only the data they need and functions that perform the required logic as parameters.
@Composable
fun LoginScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    isUserLoggedIn: MutableState<Boolean>,
    loginViewModel: LoginViewModel = viewModel()
) {
    val userName by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginImageHeader()
        LoginTitle()
        UserNameInputField(userName, loginViewModel::onUserNameChanged)
        UserPassInputField(
            password,
            isPasswordVisible,
            loginViewModel::onPasswordChanged
        ) { isPasswordVisible = !isPasswordVisible }
        ForgotPasswordTextButton(navController)
        LoginButton() {
            if (loginViewModel.performLogin(userName, password)) {
                isUserLoggedIn.value = true
                coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Logged In") }
                navController.navigateUp()
            } else {
                isUserLoggedIn.value = false
                coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Failed") }
            }
        }
    }

}