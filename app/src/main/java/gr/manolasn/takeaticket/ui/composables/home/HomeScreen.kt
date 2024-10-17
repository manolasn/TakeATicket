package gr.manolasn.takeaticket.ui.composables.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gr.manolasn.takeaticket.R
import gr.manolasn.takeaticket.ui.composables.shared.LazyColumnItem
import gr.manolasn.takeaticket.ui.composables.shared.LoadedView
import gr.manolasn.takeaticket.ui.composables.shared.TopBar
import gr.manolasn.takeaticket.ui.composables.shared.bottomSheet.BottomSheet
import gr.manolasn.takeaticket.ui.composables.shared.bottomSheet.BottomSheetViewModel
import gr.manolasn.takeaticket.ui.composables.shared.rememberScrollContext
import gr.manolasn.takeaticket.ui.theme.AppGreyBlack
import gr.manolasn.takeaticket.ui.theme.AppTeal

@Composable
fun HomeScreen(
    title: Int,
    movieClicked: (String) -> Unit,
    bottomSheetViewModel: BottomSheetViewModel = hiltViewModel(),
    searchClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {


    LaunchedEffect(Unit) {
        viewModel.getMoviesWithPagination()
    }

    val loading by viewModel.loading.collectAsState()

    val moviesList by viewModel.movies.collectAsState()

    val listState = rememberLazyListState()

    val sortByMethod by bottomSheetViewModel.selectedFilterOption.collectAsState()

    val scrollContext = rememberScrollContext(listState)


    TopBar(
        title = stringResource(id = title),
        topBarContainerColor = AppGreyBlack,
        hasNavigation = false,
        action = {


            IconButton(onClick = {
                bottomSheetViewModel.updateConnectSheetOpen()
            }) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.filter),
                    colorFilter = ColorFilter.tint(AppTeal),
                    contentDescription = "SearchIcon"
                )
            }

            IconButton(onClick = searchClicked) {
                Image(
                    imageVector = Icons.Rounded.Search,
                    colorFilter = ColorFilter.tint(AppTeal),
                    contentDescription = "SearchIcon"
                )
            }


        }
    ) {

        LoadedView(loading) {
            if (scrollContext.isBottom) {
                Log.d("HomeScreen", "Reached bottom")
                viewModel.loadMore()
            }

            Log.d("HomeScreen", "scrollContext.isBottom: ${scrollContext.isBottom}")
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(moviesList.size) { index ->
                    LazyColumnItem(
                        movie = moviesList[index],
                        onClick = { movieClicked(moviesList[index].id) },
                    )
                }
            }

        }

        BottomSheet(
            bottomSheetViewModel
        ) {
            viewModel.updateSortBy(sortByMethod)
        }


    }
}