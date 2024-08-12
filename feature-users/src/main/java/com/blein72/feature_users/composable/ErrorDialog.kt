package com.blein72.feature_users.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blein72.core.R


@Composable
fun ErrorDialog(
    errorText: String,
    onReloadClicked: () -> Unit
) {
    Box(
        modifier = Modifier

            .fillMaxSize()
    ) {
        Box( modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .align(Alignment.Center)) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
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
                    modifier = Modifier
                        .padding(top = 10.dp),
                    onClick = onReloadClicked
                ) {
                    Text(text = stringResource(R.string.basic_dialog_error_reload))
                }
            }
        }
    }
}

@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(errorText = "Error Text", onReloadClicked = {})
}
