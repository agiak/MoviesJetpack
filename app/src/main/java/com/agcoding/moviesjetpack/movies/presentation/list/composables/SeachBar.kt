package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    onTextFieldClicked: () -> Unit,
    isEnable: Boolean = true,
    isAutoFocus: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = searchQuery,
        enabled = isEnable,
        onValueChange = {
            onSearchQueryChange(it)
        },
        shape = RoundedCornerShape(100),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = MaterialTheme.colorScheme.onSurface
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.search_hint),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeSearch()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = searchQuery.isNotBlank()
            ) {
                IconButton(
                    onClick = {
                        onSearchQueryChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        modifier = modifier
            .clickable { onTextFieldClicked() }
            .background(
                shape = RoundedCornerShape(100),
                color = MaterialTheme.colorScheme.background
            )
            .minimumInteractiveComponentSize()
            .focusRequester(focusRequester)
    )

    if (isAutoFocus) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}


@PreviewLightDark
@Composable
fun SearchBarPreview() {
    MoviesJetpackTheme {
        SearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            onTextFieldClicked = {}
        )
    }
}