package com.christiansasig.movies.network.exception

import com.christiansasig.movies.network.model.error.ErrorModel

class CustomException(
    val error: ErrorModel
): Throwable()