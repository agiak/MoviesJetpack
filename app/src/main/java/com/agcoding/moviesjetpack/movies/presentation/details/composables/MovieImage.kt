package com.agcoding.moviesjetpack.movies.presentation.details.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agcoding.moviesjetpack.core.presentation.composables.buttons.BackButton
import com.agcoding.moviesjetpack.core.presentation.images.MainImage
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun MovieImage(
    imageUrl: String,
    title: String,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
    ) {
        MainImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding(),
            onClick = onBackClick
        )
        Text(
            text = title,
            color = Color.White,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 16.dp, start = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@PreviewLightDark()
@Composable
fun MovieImagePreview() {
    MoviesJetpackTheme {
        MovieImage(imageUrl = "", onBackClick = {}, title = "MovieRama movie")
    }
}