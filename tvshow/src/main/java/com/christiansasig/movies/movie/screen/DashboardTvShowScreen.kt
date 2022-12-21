package com.christiansasig.movies.movie.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.christiansasig.movies.movie.navigator.DashboardTvShowNavigatorProvider
import com.christiansasig.movies.uikit.divider.UiDivider
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
@Composable fun DashboardTvShowScreen(
    navigator: DashboardTvShowNavigatorProvider,
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { padding ->

        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            val (
                answers,
                button,
            ) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .constrainAs(answers) {
                        top.linkTo(parent.top)
                        bottom.linkTo(button.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 10.dp),
            ) {
                item {
                    Column {
                        UiTextHeadingBoldTitle(
                            modifier = Modifier
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer()
                                ),
                            text = "title",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        UiDivider()
                    }
                }

            }
            Column(modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(end = 30.dp, start = 30.dp, top = 10.dp, bottom = 10.dp)
            ) {
                /*UiSimpleButton(
                    onClick = {
                        navigator.createProposal()
                    },
                    text = stringResource(id = R.string.proposal_add_title)
                )*/
            }
        }
        LaunchedEffect(Unit) {

        }
    }
}

