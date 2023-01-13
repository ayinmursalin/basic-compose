package com.creativijaya.basiccompose.ui.main.home

import com.creativijaya.basiccompose.domain.models.QuoteDto
import com.creativijaya.basiccompose.utils.ResultWrapper
import com.creativijaya.basiccompose.utils.Uninitialized

data class HomeState(
    val counter: Int = 0,
    val quotesResult: ResultWrapper<List<QuoteDto>> = Uninitialized
)
