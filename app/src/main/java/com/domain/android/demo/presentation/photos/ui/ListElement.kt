package com.domain.android.demo.presentation.photos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun PhotoItemCard(
    modifier: Modifier,
    photo:  com.domain.android.demo.presentation.photos.model.Photo
) {
    val configuration = LocalConfiguration.current
    val width = remember(configuration) { configuration.screenWidthDp.dp }
    Card(modifier = modifier, shape = RectangleShape) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CardImage(modifier = Modifier.size(width), url = photo.url)
            CardDetails(
                modifier = Modifier.padding(16.dp),
                name = photo.title,
            )
        }
    }
}

@Composable
fun CardImage(modifier: Modifier, url:String) {
    Box(modifier = modifier) {
            AsyncImage(
                placeholder = painterResource(com.domain.android.demo.R.drawable.ic_photo),
                model =url,
                contentDescription = "contentDescription",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(10.dp)
                    .fillMaxSize()
                    .background(color = Color.LightGray, shape = RectangleShape)
            )
    }
}
@Composable
fun CardDetails(
    modifier: Modifier,
    name: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1
        )
    }
}
