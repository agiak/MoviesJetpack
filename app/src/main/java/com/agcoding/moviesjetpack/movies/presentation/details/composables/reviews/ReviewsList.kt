package com.agcoding.moviesjetpack.movies.presentation.details.composables.reviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.agcoding.moviesjetpack.core.presentation.composables.loaders.MainLoader
import com.agcoding.moviesjetpack.core.presentation.composables.messages.ErrorMessage
import com.agcoding.moviesjetpack.movies.domain.details.Review
import com.agcoding.moviesjetpack.ui.theme.primaryLight

@Composable
fun ReviewsList(
    reviews: LazyPagingItems<Review>
) {
    when (val state = reviews.loadState.refresh) {
        is LoadState.Error -> ErrorMessage(state.error.message ?: "")
        LoadState.Loading -> MainLoader(Modifier.size(48.dp))
        else -> {
            if (reviews.itemCount == 0) {
                return
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Reviews",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = primaryLight
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        items = (0 until reviews.itemCount).mapNotNull { reviews[it] },
                        key = { it.id ?: "" }
                    ) { review ->
                        ReviewItem(review = review)
                    }

                    item {
                        if (reviews.loadState.append is LoadState.Loading) {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(8.dp)
                            ) {
                                MainLoader(Modifier.size(48.dp))
                            }
                        }
                    }
                }
            }
        }
    }
} 