package com.agcoding.moviesjetpack.core.presentation.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.core.presentation.composables.loaders.MainLoader
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import com.agcoding.moviesjetpack.ui.theme.primaryContainerDark
import com.agcoding.moviesjetpack.ui.theme.primaryLight
import timber.log.Timber

@Composable
fun MainImage(
    imageUrl: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {

    if (imageUrl == null) {
        Image(
            painter = painterResource(R.drawable.ic_movie_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(primaryContainerDark),
            modifier = modifier.background(Color.LightGray)
        )
        return
    }

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            imageLoadResult =
                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Exception("Invalid image size"))
                }
        },
        onError = {
            Timber.e(it.result.throwable)
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )

    when (val result = imageLoadResult) {
        null -> MainLoader(
            modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        )

        else -> Image(
            painter = if (result.isSuccess) painter else painterResource(R.drawable.ic_error_image_loading),
            contentDescription = null,
            contentScale = if (result.isSuccess) contentScale else ContentScale.Fit,
            colorFilter = if (result.isSuccess) null else ColorFilter.tint(Color.Red),
            modifier = if (result.isSuccess) modifier else modifier.background(primaryLight)
        )
    }
}

@Composable
@Preview
fun MainImagePreview() {
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