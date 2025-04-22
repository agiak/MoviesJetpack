package com.agcoding.core.shared.presentation.composables.loaders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.agcoding.core.shared.theme.MoviesJetpackTheme

@Composable
fun MainLoader(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 4.dp
        )
    }
}

@Composable
@PreviewLightDark
fun MainLoaderPreview() {
    MoviesJetpackTheme {
        val modifier = Modifier
            .fillMaxWidth()
            .height(184.dp)
        MainLoader(modifier)
    }
}