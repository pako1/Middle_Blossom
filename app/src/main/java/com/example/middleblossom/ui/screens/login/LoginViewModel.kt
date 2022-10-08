package com.example.middleblossom.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("test")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("test")
    val password = _password.asStateFlow()

    fun onUserNameChanged(newUserName: String) {
        _email.value = newUserName.trim()
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword.trim()
    }

    fun performLogin(email: String, password: String): Boolean {
        return email == "test" && password == "test"
    }

}