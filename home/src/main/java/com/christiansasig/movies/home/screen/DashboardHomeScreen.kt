package com.christiansasig.movies.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.christiansasig.movies.core.flow.rememberFlowWithLifecycle
import com.christiansasig.movies.home.navigator.DashboardHomeNavigatorProvider
import com.christiansasig.movies.home.uistate.HomeUiState
import com.christiansasig.movies.home.viewmodel.HomeViewModel
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
    val homeUiState by rememberFlowWithLifecycle(viewModel.homeUiState)
        .collectAsState(initial = HomeUiState.Empty)

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background)
        ) {

            item {
                Column(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 30.dp),
                ) {

                }
            }


        }

        LaunchedEffect(Unit) {
            viewModel.getHomeInfo()
        }
    }
}

