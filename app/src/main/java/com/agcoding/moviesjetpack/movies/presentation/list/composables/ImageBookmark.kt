package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.core.presentation.images.MainImage
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun ImageBookmark(
    imageUrl: String,
    isFavourite: Boolean = false,
    onFavouriteClicked: () -> Unit
) {
    var isClicked by remember { mutableStateOf(isFavourite) }

    Box {
        MainImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(184.dp)
        )
        Surface(
            shadowElevation = 19.dp,
            color = Color.Transparent,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = 12.dp)
                .clickable {
                    onFavouriteClicked()
                    isClicked = !isClicked
                }
        ) {
            Icon(
                painter = if (isClicked)
                    painterResource(R.drawable.ic_bookmar_filled)
                else
                    painterResource(R.drawable.ic_bookmar),
                contentDescription = "Favourite button",
                tint = Color.Yellow,
                modifier = Modifier
                    .size(36.dp)
            )
        }
    }
}

@Preview
@Composable
fun ImageBookmarkPreview() {
    MoviesJetpackTheme {
        ImageBookmark(
            "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
        ) {}
    }
}