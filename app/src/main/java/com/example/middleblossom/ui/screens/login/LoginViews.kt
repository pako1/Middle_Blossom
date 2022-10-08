@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)

package com.example.middleblossom.ui.screens.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.middleblossom.R
import com.example.middleblossom.ui.theme.Dimension
import com.example.middleblossom.ui.theme.TextSize
import kotlinx.coroutines.launch

@Composable
fun LoginImageHeader() = Image(
    painter = painterResource(id = R.drawable.ic_undraw_welcome_re_h3d9_1_),
    contentDescription = "",
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = Dimension.Normal.size)
        .aspectRatio(1.8f)
)

@Composable
fun LoginTitle() =
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.Normal.size,
                bottom = Dimension.Big.size
            ),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "Login", fontSize = TextSize.VeryBig.size, fontWeight = FontWeight.Bold)
    }

@Composable
fun UserNameInputField(userNameText: String, onUserNameChanged: (String) -> Unit) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    TextField(
        value = userNameText,
        placeholder = { Text(text = "Email ID") },
        onValueChange = onUserNameChanged,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimension.Normal.size, end = Dimension.Normal.size)
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


@Composable
fun UserPassInputField(
    passText: String,
    isPasswordVisible: Boolean,
    onPasswordChanged: (String) -> Unit,
    onIconClick: () -> Unit
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    TextField(
        value = passText,
        onValueChange = onPasswordChanged,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        placeholder = { Text(text = "Password") },
        trailingIcon = {
            val image = if (isPasswordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            IconButton(onClick = onIconClick) {
                Icon(imageVector = image, "")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.Normal.size,
                top = Dimension.Medium.size,
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

@Composable
fun LoginButton(onLoginButtonClicked: () -> Unit) = Button(
    onClick = onLoginButtonClicked,
    modifier = Modifier
        .fillMaxWidth()
        .padding(Dimension.Normal.size)
        .heightIn(min = 46.dp),
    shape = RoundedCornerShape(50), // = 50% percent
    content = { Text(text = "Login") }
)


@Composable
fun ForgotPasswordTextButton(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimension.Normal.size)
    ) {
        Text(
            text = "Forgot Password?",
            modifier = Modifier
                .clip(RoundedCornerShape(Dimension.Medium.size))
                .clickable {
                    /* navController.navigateTo(Destination.ForgotPassword)*/
                }
                .padding(Dimension.Small.size),
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
        )
    }
}
