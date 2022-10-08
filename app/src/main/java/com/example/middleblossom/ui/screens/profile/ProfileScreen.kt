package com.example.middleblossom.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen() {
    val data =
        listOf("First name" to "Filippos", "Last name" to "Budha", "Date of Birth" to "22.06.1990", "Phone Number" to "+493242343242", "Customer ID" to "321Ada2")

    Column {
        ProfileHeader()
        ProfileImage()
        PersonalData(data)
    }
}