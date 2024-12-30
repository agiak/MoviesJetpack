package com.agcoding.moviesjetpack.core.presentation.composables.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = text)
    }
}

@Composable
@Preview
fun PrimaryButtonPreview() {
    MoviesJetpackTheme {
        Box(
            modifier = Modifier.padding(
                horizontal = 24.dp,
                vertical = 24.dp
            )
        ) {
            PrimaryButton(
                text = "Primary Button",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}