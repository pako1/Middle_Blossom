package com.example.middleblossom.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun DefaultSnackBar(data: SnackbarHostState) =
    SnackbarHost(hostState = data, snackbar = { SnackBar(data = it) })


@Composable
fun SnackBar(data: SnackbarData) = Snackbar(
    actionColor = MaterialTheme.colors.onPrimary,
    snackbarData = data,
    backgroundColor = MaterialTheme.colors.primary
)