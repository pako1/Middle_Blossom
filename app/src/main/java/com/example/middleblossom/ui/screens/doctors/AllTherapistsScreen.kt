package com.example.middleblossom.ui.screens.doctors

import androidx.compose.runtime.Composable
import com.example.middleblossom.domain.models.Therapist

val therapists = listOf(
    Therapist("Antonio Derluko", "Physiotherapist", "4.9"),
    Therapist("Chris Dento", "Physiotherapist", "4.3"),
    Therapist("Filka Tenso", "Physiotherapist", "4.2"),
    Therapist("Frinto Betsi", "Physiotherapist", "4.1"),
    Therapist("Filka Tenso", "Physiotherapist", "4.0"),
    Therapist("Filka Tenso", "Physiotherapist", "4.0"),
    Therapist("Filka Tenso", "Physiotherapist", "3.8"),
    Therapist("Filka Tenso", "Physiotherapist", "3.8"),
    Therapist("Filka Tenso", "Physiotherapist", "3.7"),
    Therapist("Filka Tenso", "Physiotherapist", "3.7"),
    Therapist("Filka Tenso", "Physiotherapist", "3.4"),
    Therapist("Filka Tenso", "Physiotherapist", "3.4"),
    Therapist("Filka Tenso", "Physiotherapist", "3.2"),
    Therapist("Filka Tenso", "Physiotherapist", "3.2"),
    Therapist("Filka Tenso", "Physiotherapist", "3.0"),

    )

@Composable
fun AllDoctorsScreen() = AllTherapists()