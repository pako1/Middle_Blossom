package com.example.middleblossom.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class Dimension(val size: Dp) {
    object Small : Dimension(4.dp)
    object Medium : Dimension(8.dp)
    object Normal : Dimension(16.dp)
    object Big : Dimension(24.dp)
    object VeryBig : Dimension(32.dp)
}