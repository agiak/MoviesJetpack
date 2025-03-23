package com.agcoding.moviesjetpack.movies.presentation.details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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
            .height(400.dp)
    ) {
        MainImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        // Dark scrim overlay with multiple gradient stops
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.4f), // Top overlay
                            Color.Black.copy(alpha = 0.0f), // Transparent middle
                            Color.Black.copy(alpha = 0.6f), // Gradual darkening
                            Color.Black.copy(alpha = 0.9f)  // Dark bottom
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 48.dp, start = 16.dp),
            onClick = onBackClick
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.75f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )
        }
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