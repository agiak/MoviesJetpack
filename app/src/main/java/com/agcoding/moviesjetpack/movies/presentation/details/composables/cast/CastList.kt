package com.agcoding.moviesjetpack.movies.presentation.details.composables.cast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agcoding.moviesjetpack.movies.domain.details.CastDetails
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun CastList(
    castList: List<CastDetails>,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Cast",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(castList) { castMember ->
                CastItem(cast = castMember)
            }
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun CastListPreview() {
    val castList = listOf(
        CastDetails(id = 1023, name = "John Smith", image = null, character = "Detective"),
        CastDetails(id = 8765, name = "Emily Davis", image = null, character = "Hero"),
        CastDetails(id = 4532, name = "Michael Johnson", image = null, character = "Villain"),
        CastDetails(id = 7890, name = "Sophia Lee", image = null, character = "Sidekick"),
        CastDetails(id = 2145, name = "Daniel Martinez", image = null, character = "Mentor")
    )
    MoviesJetpackTheme {
        CastList(
            castList
        )
    }
}