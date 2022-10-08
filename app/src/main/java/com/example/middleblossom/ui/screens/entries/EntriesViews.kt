@file:OptIn(ExperimentalMaterialApi::class)

package com.example.middleblossom.ui.screens.entries

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.middleblossom.domain.models.Day
import com.example.middleblossom.domain.models.Entry
import com.example.middleblossom.ui.theme.Dimension
import com.example.middleblossom.ui.theme.TextSize
import com.example.middleblossom.ui.utils.DateHelper

@Composable
fun EntriesHeader(
    pickedMonth: String,
    updatePickedMonth: (String) -> Unit,
    expanded: Boolean,
    onDropDownClick: (Boolean) -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.Normal.size,
                top = Dimension.Big.size,
                bottom = Dimension.Big.size
            )
    ) {
        Row(modifier = Modifier
            .clickable { onDropDownClick.invoke(!expanded) }
            .padding(Dimension.Medium.size)) {
            val buildingText = buildAnnotatedString {
                append("Your entries in")
                withStyle(style = SpanStyle(Color.Blue)) {
                    append(" $pickedMonth")
                }
            }
            Text(
                text = buildingText,
                fontSize = TextSize.Big.size,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier.align(Bottom)
            )
            DropdownMenu(expanded = expanded, onDismissRequest = {
                onDropDownClick.invoke(false)
            }) {
                DateHelper.getMonths().forEach { month ->
                    DropdownMenuItem(onClick = {
                        onDropDownClick.invoke(false)
                        updatePickedMonth.invoke(month)
                    }) {
                        Text(text = month)
                    }
                }
            }
        }
    }
}

@Composable
fun Month(
    days: List<Day>,
    indexOfPicked: Int,
    updatePickedIndex: (Int) -> Unit
) {
    LazyRow(contentPadding = PaddingValues(Dimension.Medium.size)) {
        itemsIndexed(days) { index, day ->
            if (indexOfPicked == index)
                DayItem(true, day, updatePickedIndex, indexOfPicked)
            else
                DayItem(false, day, updatePickedIndex, index)

        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DayItem(
    isDayPicked: Boolean,
    day: Day,
    updatePickedIndex: (Int) -> Unit,
    index: Int
) {
    val transition = updateTransition(targetState = isDayPicked, label = "dayTransition")
    val size by transition.animateDp(label = "dayTransition") { isDayPickedState ->
        if (isDayPickedState) 100.dp else 80.dp
    }
    val color by transition.animateColor(label = "dayTransition") { isDayPickedState ->
        if (isDayPickedState) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    }
    Card(
        onClick = { updatePickedIndex.invoke(index) },
        modifier = Modifier
            .padding(Dimension.Medium.size)
            .widthIn(min = 70.dp)
            .height(size),
        backgroundColor = color,
        shape = RoundedCornerShape(Dimension.Big.size)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = day.numberOfMonth,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = Dimension.Medium.size
                )
            )
            Text(
                text = day.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(
                    top = Dimension.Small.size,
                    start = Dimension.Normal.size,
                    end = Dimension.Normal.size
                )
            )
            transition.AnimatedVisibility(
                visible = { it },
                enter = fadeIn(animationSpec = tween(700)) + scaleIn(animationSpec = tween(700))
            ) {
                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(Dimension.Normal.size)
                        .padding(top = Dimension.Medium.size)
                )
            }
        }
    }
}

@Composable
fun Entries(entries: List<Entry>, onItemClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = Dimension.Normal.size),
        contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp)
    ) {
        items(entries) {
            DateEntry(it)
            EntryItem(it, onItemClick)
        }
    }
}

val emojis = listOf(
    "\uD83D\uDE03",
    "\uD83E\uDD12",
    "\uD83E\uDD22",
    "\uD83E\uDD74",
    "\uD83D\uDCA9",
    "\uD83E\uDD27"
)

@Composable
fun DateEntry(entry: Entry) {
    Text(
        text = "${entry.time.hour}:${entry.time.minute}",
        Modifier.padding(
            start = Dimension.Normal.size,
            bottom = Dimension.Medium.size,
            top = Dimension.Big.size
        ),
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun EntryItem(entry: Entry, onItemClick: () -> Unit) {
    Card(
        elevation = Dimension.Small.size,
        onClick = onItemClick,
        modifier = Modifier
            .fillMaxWidth()
            .size(90.dp)
            .padding(Dimension.Medium.size),
        shape = RoundedCornerShape(Dimension.Normal.size)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        start = Dimension.Normal.size,
                        bottom = Dimension.Medium.size,
                        top = Dimension.Medium.size
                    )
                    .clip(RoundedCornerShape(Dimension.Normal.size))
                    .background(MaterialTheme.colors.secondary)
                    .padding(start = Dimension.Normal.size, end = Dimension.Normal.size)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(text = emojis.random(), fontSize = TextSize.Big.size)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = Dimension.Big.size),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = entry.generalFeeling, fontWeight = FontWeight.Bold)
                Text(
                    text = entry.sicknessFeeling, fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}