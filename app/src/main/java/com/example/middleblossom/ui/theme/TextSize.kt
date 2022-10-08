package com.example.middleblossom.ui.theme

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class TextSize(val size: TextUnit) {
    object Small : TextSize(4.sp)
    object Medium : TextSize(8.sp)
    object Semi : TextSize(12.sp)
    object Normal : TextSize(16.sp)
    object Big : TextSize(24.sp)
    object VeryBig : TextSize(32.sp)
}