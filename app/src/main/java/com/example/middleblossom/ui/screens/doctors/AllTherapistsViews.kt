package com.example.middleblossom.ui.screens.doctors

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.middleblossom.ui.screens.home.TherapistItem

@Composable
fun AllTherapists() = LazyColumn() {
    items(therapists) {
        TherapistItem(therapist = it) {}
    }
}
