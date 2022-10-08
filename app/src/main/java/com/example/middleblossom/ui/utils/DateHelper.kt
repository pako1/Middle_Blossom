package com.example.middleblossom.ui.utils

import com.example.middleblossom.domain.models.Day
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.*

object DateHelper {

    fun getDaysOfMonth(month: String): List<Day> {
        var currentDate = LocalDate.of(LocalDate.now().year, Month.valueOf(month.uppercase()), 1)
        val end = currentDate.plusMonths(1)
        val days = mutableListOf<Day>()
        while (currentDate.isBefore(end)) {
            days.add(
                Day(
                    name = currentDate.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    ), numberOfMonth = currentDate.dayOfMonth.toString()
                )
            )
            currentDate = currentDate.plusDays(1)
        }
        return days
    }

    fun getMonths(): List<String> {
        return Month.values()
            .map { it -> it.toString().lowercase().replaceFirstChar { it.uppercaseChar() } }
            .toList()
    }

    fun getCurrentMonth(): String {
        val currentMonth = LocalDate.now().month.name
        return getMonths().find { it.equals(currentMonth, ignoreCase = true) }.toString()
    }
}