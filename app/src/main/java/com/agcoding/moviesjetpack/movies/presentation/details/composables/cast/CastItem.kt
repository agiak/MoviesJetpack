package com.agcoding.moviesjetpack.movies.presentation.details.composables.cast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agcoding.moviesjetpack.core.presentation.images.MainImage
import com.agcoding.moviesjetpack.movies.domain.details.CastDetails
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun CastItem(
    cast: CastDetails
) {
    Column(
        modifier = Modifier.width(124.dp)
    ) {
        MainImage(
            imageUrl = cast.image ?: "",
            modifier = Modifier
                .size(124.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Text(
            text = cast.name,
            style = TextStyle(fontWeight = FontWeight.W300, fontSize = 12.sp),
            maxLines = 2,
        )
        Text(
            text = cast.character,
            style = TextStyle(fontWeight = FontWeight.Thin, fontSize = 10.sp),
            maxLines = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CastItemPreview() {
    MoviesJetpackTheme {
        CastItem(
            cast = CastDetails(
                id = 1,
                name = "Anastastios\nGiakoumatos",
                character = "Captain Jack Sparrow",
                image = null
            )
        )
    }
}