package com.christiansasig.movies.movie.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.christiansasig.movies.core.R
import com.christiansasig.movies.core.flow.rememberFlowWithLifecycle
import com.christiansasig.movies.movie.navigator.DashboardMovieNavigatorProvider
import com.christiansasig.movies.movie.uistate.MovieUiState
import com.christiansasig.movies.movie.viewmodel.MovieViewModel
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
@Composable fun DashboardMovieScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    navigator: DashboardMovieNavigatorProvider,
) {
    val movieUiState by rememberFlowWithLifecycle(viewModel.movieUiState)
        .collectAsState(initial = MovieUiState.Empty)

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
                middlePanel,
            ) = createRefs()

            Column(modifier = Modifier
                .constrainAs(topPanel) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 10.dp)
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

            LazyVerticalGrid(
                columns = GridCells.Adaptive(90.dp),
                modifier = Modifier
                    .constrainAs(middlePanel) {
                        top.linkTo(topPanel.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(items = movieUiState.data?.results ?: emptyList()) { item ->
                    UiRowMovie(
                        title = item.title ?: "",
                        imageUrl =item.posterPath
                    )
                }
            }
        }
        LaunchedEffect(Unit) {
            viewModel.getPopularMovies()
        }
    }
}

