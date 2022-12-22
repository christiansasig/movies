package com.christiansasig.movies.home.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.christiansasig.movies.core.R
import com.christiansasig.movies.core.flow.rememberFlowWithLifecycle
import com.christiansasig.movies.home.navigator.DashboardHomeNavigatorProvider
import com.christiansasig.movies.home.uistate.MovieNowPlayingUiState
import com.christiansasig.movies.home.uistate.MovieUiState
import com.christiansasig.movies.home.uistate.TvUiState
import com.christiansasig.movies.home.viewmodel.HomeViewModel
import com.christiansasig.movies.uikit.divider.UiDivider
import com.christiansasig.movies.uikit.row.UiRowMovie
import com.christiansasig.movies.uikit.text.UiTextHeadingBoldTitle
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(
    start = true
)
@Destination
@Composable
fun DashboardHomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DashboardHomeNavigatorProvider,
) {
    val movieUiState by rememberFlowWithLifecycle(viewModel.movieUiState)
        .collectAsState(initial = MovieUiState.Empty)

    val tvUiState by rememberFlowWithLifecycle(viewModel.tvUiState)
        .collectAsState(initial = TvUiState.Empty)

    val movieNowPlayingUiState by rememberFlowWithLifecycle(viewModel.movieNowPlayingUiState)
        .collectAsState(initial = MovieNowPlayingUiState.Empty)


    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (
                topPanel,
            ) = createRefs()

            LazyColumn(
                modifier = Modifier
                    .constrainAs(topPanel) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 10.dp),
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        UiTextHeadingBoldTitle(
                            modifier = Modifier
                                .placeholder(
                                    visible = movieUiState.isLoading,
                                    highlight = PlaceholderHighlight.shimmer()
                                ),
                            text = stringResource(id = R.string.popular_movie_title),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        UiDivider()
                    }
                }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(items = movieUiState.data?.results ?: emptyList()) { item ->
                            UiRowMovie(
                                modifier = Modifier.width(110.dp),
                                title = item.title ?: "",
                                imageUrl = item.posterPath
                            )
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        UiTextHeadingBoldTitle(
                            modifier = Modifier
                                .placeholder(
                                    visible = tvUiState.isLoading,
                                    highlight = PlaceholderHighlight.shimmer()
                                ),
                            text = stringResource(id = R.string.popular_tv_show_title),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        UiDivider()
                    }
                }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(items = tvUiState.data?.results ?: emptyList()) { item ->
                            UiRowMovie(
                                modifier = Modifier.width(110.dp),
                                title = item.name ?: "",
                                imageUrl = item.posterPath
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        UiTextHeadingBoldTitle(
                            modifier = Modifier
                                .placeholder(
                                    visible = movieNowPlayingUiState.isLoading,
                                    highlight = PlaceholderHighlight.shimmer()
                                ),
                            text = stringResource(id = R.string.now_playing_title),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        UiDivider()
                    }
                }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(items = movieNowPlayingUiState.data?.results ?: emptyList()) { item ->
                            UiRowMovie(
                                modifier = Modifier.width(110.dp),
                                title = item.title ?: "",
                                imageUrl = item.posterPath
                            )
                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            viewModel.getPopularMovies()
            viewModel.getPopularTv()
            viewModel.getMoviesNowPlaying()
        }
    }
}

