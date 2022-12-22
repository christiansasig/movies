package com.christiansasig.movies.core.strings

import com.christiansasig.movies.core.BuildConfig

fun String.toUrlImage(): String =
    BuildConfig.HOST_IMAGE + this