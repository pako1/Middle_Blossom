@file:OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)

package com.example.middleblossom.ui.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.middleblossom.R
import com.example.middleblossom.ui.theme.Dimension
import kotlinx.coroutines.launch

@Composable
fun ProfileHeader() = Text(
    text = "Personal data",
    style = MaterialTheme.typography.h6,
    fontWeight = FontWeight.Bold,
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth()
        .padding(top = Dimension.Normal.size)
)

@Composable
fun ProfileImage() {
    Spacer(modifier = Modifier.padding(Dimension.Normal.size))
    Image(
        painter = painterResource(id = R.drawable.ic_undraw_doctor_kw_5_l),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3.5f),
        contentScale = ContentScale.FillHeight
    )
}

@Composable
fun PersonalData(data: List<Pair<String, String>>) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = Dimension.Normal.size,
            bottom = Dimension.Normal.size
        )
    ) {
        items(data) {
            PersonalItemTitle(it.first)
            PersonalItemDetail(it.second)
        }
    }
}

@Composable
fun PersonalItemTitle(personalDataTitle: String) = Text(
    text = personalDataTitle,
    Modifier.padding(bottom = Dimension.Medium.size, start = Dimension.Big.size)
)

@Composable
fun PersonalItemDetail(personalData: String) {
    var userInfo by remember { mutableStateOf(TextFieldValue(personalData)) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = userInfo,
        onValueChange = { userInfo = it },
        placeholder = { Text(text = "Enter info") },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(Dimension.Normal.size),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.Normal.size,
                end = Dimension.Normal.size,
                bottom = Dimension.Normal.size
            )
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
    )
}