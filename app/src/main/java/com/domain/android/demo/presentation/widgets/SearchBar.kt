package com.domain.android.demo.presentation.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.domain.android.demo.presentation.theme.SampleAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    placeHolderText: String,
    onSearchTextChanged: (String) -> Unit,
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    Card(
        shape = androidx.compose.ui.graphics.RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
    ) {
        TextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchTextChanged(it)
            },
            placeholder = {
                Text(text = placeHolderText)
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleAppTheme {
        SearchBar("Search...") {

        }
    }
}
