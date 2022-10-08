@file:OptIn(ExperimentalMaterialApi::class)

package com.example.middleblossom.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.middleblossom.R
import com.example.middleblossom.domain.models.Sickness
import com.example.middleblossom.domain.models.Therapist
import com.example.middleblossom.ui.theme.Dimension
import com.example.middleblossom.ui.theme.TextSize
import com.example.middleblossom.ui.theme.Yellow
import kotlin.random.Random

@Composable
fun Greetings(modifier: Modifier, text: String) = Text(
    text = text,
    fontWeight = FontWeight.Bold,
    style = typography.h4,
    color = MaterialTheme.colors.onBackground,
    modifier = modifier
)

@Composable
fun CardAction(
    @DrawableRes cardActionIcon: Int,
    @StringRes cardActionText: Int,
    cardActionBackgroundColor: Color,
    modifier: Modifier
) = Card(
    shape = RoundedCornerShape(Dimension.Medium.size),
    backgroundColor = cardActionBackgroundColor,
    modifier = modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(Dimension.Normal.size)
            .heightIn(max = 120.dp)
    ) {
        Text(
            text = stringResource(id = cardActionText),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(Dimension.Medium.size))
        Image(
            painter = painterResource(id = cardActionIcon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun ListTitle(modifier: Modifier, title: String) = Text(
    text = title, fontWeight = FontWeight.Bold,
    style = typography.h6,
    modifier = modifier
)

@Composable
fun Symptoms(sicknessList: List<Sickness>, onItemClick: () -> Unit) {
    LazyRow(contentPadding = PaddingValues(start = Dimension.Normal.size)) {
        items(sicknessList) {
            SicknessItem(sickness = it, onItemClick)
        }
    }
}

@Composable
fun SicknessItem(sickness: Sickness, onItemClick: () -> Unit) {
    Card(
        onClick = onItemClick,
        modifier = Modifier
            .padding(
                top = Dimension.Medium.size,
                bottom = Dimension.Medium.size,
                end = Dimension.Medium.size
            )
    ) {
        Column {
            Text(
                text = sickness.symptom,
                style = typography.h6,
                modifier = Modifier.padding(Dimension.Medium.size)
            )
        }
    }
}

@Composable
fun TopFourTherapists(therapists: List<Therapist>, onItemClick: () -> Unit) {
    therapists.forEach {
        TherapistItem(it, onItemClick)
    }
}

@Composable
fun SeeAllText(modifier: Modifier) = Text(text = "See all", modifier = modifier)


@Composable
fun TherapistItem(therapist: Therapist, onItemClick: () -> Unit) {
    Card(
        onClick = onItemClick,
        Modifier
            .fillMaxWidth()
            .padding(
                top = Dimension.Medium.size,
                bottom = Dimension.Medium.size,
                start = Dimension.Normal.size,
                end = Dimension.Normal.size
            )
    ) {
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = getRandomProfileIcon()),
                contentDescription = "",
                modifier = Modifier.padding(Dimension.Medium.size),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = Dimension.Normal.size)
            ) {
                Text(text = therapist.name)
                Text(text = therapist.expertise, fontSize = TextSize.Semi.size)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.End)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        tint = Yellow,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(Dimension.Small.size)
                            .height(Dimension.Big.size)
                            .rotate(-5f)
                    )
                    Text(
                        text = therapist.rating,
                        modifier = Modifier
                            .padding(end = Dimension.Normal.size)
                            .clickable {
                            },
                    )
                }
            }
        }
    }
}

@DrawableRes
private fun getRandomProfileIcon() =
    if (Random.nextInt() % 2 == 0) R.drawable.ic_temp1 else R.drawable.ic_temp2


