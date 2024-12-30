package com.agcoding.moviesjetpack.core.presentation.composables.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun ErrorMessage(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
        )
    }
}

@Composable
@Preview
fun ErrorMessagePreview() {
    MoviesJetpackTheme {
        ErrorMessage(message = "Something went wrong")
    }
}