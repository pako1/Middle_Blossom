package com.example.middleblossom.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.middleblossom.R
import com.example.middleblossom.domain.models.Sickness
import com.example.middleblossom.domain.models.Therapist
import com.example.middleblossom.ui.navigation.Destination
import com.example.middleblossom.ui.navigation.navigateTo
import com.example.middleblossom.ui.theme.BlossomBlue
import com.example.middleblossom.ui.theme.Dimension
import kotlinx.coroutines.launch

val topTherapists = listOf(
    Therapist("Antonio Derluko", "Physiotherapist", "4.9"),
    Therapist("Chris Dento", "Radiologists", "4.3"),
    Therapist("Filka Tenso", "Oncologist", "4.2"),
    Therapist("Frinto Betsi", "Cardiothoracic surgeon", "4.1"),
)

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    isUserLoggedIn: MutableState<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Greetings(
            modifier = Modifier.padding(
                start = Dimension.Normal.size,
                top = Dimension.Normal.size,
                bottom = Dimension.Normal.size
            ).fillMaxWidth(),
            text = if (isUserLoggedIn.value) "Hello, Filippos\uD83D\uDC4B" else "Hello \uD83D\uDC4B"
        )
        Row() {
            CardAction(
                cardActionIcon = R.drawable.ic_undraw_pilates_gpdb,
                cardActionText = R.string.home_action_start_training,
                cardActionBackgroundColor = BlossomBlue,
                Modifier
                    .padding(start = Dimension.Normal.size, end = Dimension.Medium.size)
                    .weight(1f)
                    .clickable { navController.navigateTo(Destination.Exercise) }
            )
            CardAction(
                cardActionIcon = R.drawable.ic_undraw_date_picker_re_r0p8,
                cardActionText = R.string.home_action_treatment_plan,
                cardActionBackgroundColor = BlossomBlue,
                Modifier
                    .padding(start = Dimension.Medium.size, end = Dimension.Normal.size)
                    .weight(1f)
                    .clickable { navController.navigateTo(Destination.TreatmentPlan) }
            )
        }
        ListTitle(
            modifier = Modifier.padding(
                start = Dimension.Normal.size,
                top = Dimension.Normal.size
            ),
            title = "What are your symptoms today?"
        )
        Symptoms(
            listOf(
                Sickness("I'm fine \uD83D\uDE03"),
                Sickness("Fever \uD83E\uDD12"),
                Sickness("vomiting \uD83E\uDD22"),
                Sickness("Dizziness \uD83E\uDD74"),
                Sickness("Diarrhoea \uD83D\uDCA9"),
                Sickness("Sneezing \uD83E\uDD27")
            ),
            onItemClick = {
                coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar(message = "Thanks for the input") }
            }
        )
        Row(Modifier.fillMaxWidth()) {
            ListTitle(
                modifier = Modifier
                    .padding(
                        start = Dimension.Normal.size,
                        top = Dimension.Big.size
                    )
                    .weight(5f),
                title = "Popular Therapists"
            )
            SeeAllText(modifier = Modifier
                .clickable { navController.navigateTo(Destination.AllDoctors) }
                .weight(1f)
                .align(alignment = Alignment.Bottom))
        }
        TopFourTherapists(
            therapists = topTherapists,
            onItemClick = {
                coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar(message = "Thanks for the input") }
            }
        )
    }

}