package com.domain.android.demo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.domain.android.demo.presentation.photos.ui.PhotosScreen
import com.domain.android.demo.presentation.theme.SampleAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                PhotosScreen(viewModel = koinViewModel(), onBack = ::finish)
            }
        }
    }
}
