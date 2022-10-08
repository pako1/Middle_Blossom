package com.example.middleblossom.ui.screens.entries

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.example.middleblossom.domain.models.Day
import com.example.middleblossom.domain.models.Entry
import com.example.middleblossom.ui.utils.DateHelper
import java.time.LocalDateTime

@Composable
fun EntriesScreen() {
    var expanded by remember { mutableStateOf(false) }
    var currentPickedMonth by remember { mutableStateOf(DateHelper.getCurrentMonth()) }
    val daysOfMonth = remember { mutableStateListOf<Day>() }
    val isDayPicked by remember { mutableStateOf(false) }
    var indexOfPicked by remember { mutableStateOf(-1) }

    Column {
        EntriesHeader(
            pickedMonth = currentPickedMonth,
            updatePickedMonth = { currentPickedMonth = it },
            expanded = expanded,
            onDropDownClick = { expanded = !expanded }
        )

        Month(
            days = daysOfMonth.apply {
                clear()
                addAll(DateHelper.getDaysOfMonth(currentPickedMonth))
            },
            indexOfPicked = indexOfPicked,
            updatePickedIndex = { indexOfPicked = if (isDayPicked) -1 else it })

        Entries(
            entries = listOf(
                Entry(
                    "feeling angry",
                    "Fever",
                    LocalDateTime.now()
                ),
                Entry(
                    "feeling angry",
                    "Fever",
                    LocalDateTime.now()
                ),
                Entry(
                    "feeling angry",
                    "Fever",
                    LocalDateTime.now()
                )
            ), onItemClick = {}
        )
    }
}