package com.example.middleblossom.ui.navigation

sealed class Destination(val id: String) {
    object Splash : Destination("splash")
    object Home : Destination("home")
    object AllDoctors : Destination("all_doctors")
    object Exercise : Destination("excercise")
    object TreatmentPlan : Destination("treatment_plan")
    object Finish : Destination("finish")
    object Login : Destination("login")
    object ForgotPassword : Destination("forgot_password")
    object Entries : Destination("entries")
    object Profile : Destination("profile")
}