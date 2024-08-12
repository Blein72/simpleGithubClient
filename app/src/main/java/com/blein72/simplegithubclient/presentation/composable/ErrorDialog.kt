package com.blein72.simplegithubclient.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.blein72.simplegithubclient.R

@Composable
fun ErrorDialog(
    errorText: String,
    onReloadClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(
                    R.string.something_went_wrong_tou_can_try_to_reload_data_with_error,
                    errorText
                )
            )
            Button(
                onClick = onReloadClicked
            ) {
                Text(text = stringResource(R.string.basic_dialog_error_reload))
            }
        }
    }
}
