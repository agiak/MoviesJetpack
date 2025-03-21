package com.agcoding.moviesjetpack.core.presentation.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import timber.log.Timber

@Composable
fun MainImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true) // Smooth transition when the image loads
                .build(),
            contentDescription = "Loaded Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale,
            onSuccess = {
                isLoading = false
                hasError = false
            },
            onError = { error ->
                Timber.e(error.result.throwable)
                isLoading = false
                hasError = true
            },
            placeholder = painterResource(id = R.drawable.ic_movie_logo), // Placeholder while loading
        )

        if (isLoading) {
            //CircularProgressIndicator(modifier = Modifier.align())
        }

        if (hasError && imageUrl.isNotBlank()) {
            Image(
                painter = painterResource(id = R.drawable.ic_error_image_loading),
                contentDescription = "Error Image",
                contentScale = contentScale,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
@PreviewLightDark
fun CoilImageWithLoadingAndErrorPreview() {
    val URL_POSTER = "http://image.tmdb.org/t/p/w500"
    MoviesJetpackTheme {
        MainImage(
            imageUrl = "$URL_POSTER/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.65f, matchHeightConstraintsFirst = false)
        )
    }
}