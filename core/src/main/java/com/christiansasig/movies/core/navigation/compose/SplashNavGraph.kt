package com.christiansasig.movies.core.navigation.compose

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@NavGraph
annotation class SplashNavGraph(
    val start: Boolean = false
)