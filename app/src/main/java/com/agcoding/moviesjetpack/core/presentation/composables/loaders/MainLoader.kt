package com.agcoding.moviesjetpack.core.presentation.composables.loaders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun MainLoader(
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun MainLoaderPreview() {
    MoviesJetpackTheme {
        val modifier = Modifier
            .fillMaxWidth()
            .height(184.dp)
        MainLoader(modifier)
    }
}