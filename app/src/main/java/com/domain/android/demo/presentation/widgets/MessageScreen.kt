package com.domain.android.demo.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MessageScreen(
    text: String, buttonText: String? = null, onClick: () -> Unit={}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )
        buttonText?.let {
            Button(onClick = onClick) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    MessageScreen(text = "In order to get the best experience, please enable permissions ",
        buttonText = "Go to settings",
        onClick = { })
}
