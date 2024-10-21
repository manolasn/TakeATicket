package gr.manolasn.takeaticket.ui.composables.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.domain.model.movie.searchMovies.toMovie
import gr.manolasn.takeaticket.ui.composables.shared.LazyColumnItem
import gr.manolasn.takeaticket.ui.composables.shared.LoadedView
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal
import gr.manolasn.takeaticket.ui.theme.Typography
import gr.manolasn.takeaticket.ui.theme.White

@Composable
fun SearchScreen(
    goBack: () -> Unit,
    movieClicked: (String) -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    TopBar(
        title = stringResource(id = R.string.search_title),
        hasNavigation = true,
        onBackClick = goBack,
        topBarContainerColor = AppGreyBlack,
        textColor = White
    ) {

        val loading by viewModel.loading.collectAsState()

        val searchResults by viewModel.searchResults.collectAsState()


        LoadedView(loading) {
            Column(
                modifier = Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                var searchItem by remember { mutableStateOf(TextFieldValue()) }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(48.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                        .shadow(elevation = 16.dp, shape = RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    value = searchItem,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = AppTeal,
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        unfocusedTextColor = White,

                        ),
                    singleLine = true,
                    onValueChange = { textFieldValue ->
                        searchItem = textFieldValue
                    },
                    trailingIcon = {
                        IconButton(modifier = Modifier
                            .height(48.dp)
                            .background(
                                color = AppGreyBlack,
                                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                            ), onClick = {
                            viewModel.searchMovies(searchItem.text)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search icon",
                                tint = AppTeal
                            )
                        }
                    },
                    leadingIcon = {

                        IconButton(onClick = { searchItem = TextFieldValue("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "clear text icon"
                            )
                        }

                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.searchMovies(searchItem.text)
                    })
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = stringResource(R.string.results),
                    style = Typography.bodyMedium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    color = AppGreyBlack
                )
                Spacer(modifier = Modifier.height(2.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(searchResults.size) { index ->
                        LazyColumnItem(
                            movie = searchResults[index].toMovie(),
                            onClick = { movieClicked(searchResults[index].id) },
                        )
                    }
                }


            }
        }

    }
}