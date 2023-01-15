package com.creativijaya.basiccompose.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativijaya.basiccompose.domain.usecases.GetQuotesUseCase
import com.creativijaya.basiccompose.domain.usecases.InsertQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("initialCounter") private val initialCounter: Int,
    getQuotesUseCase: GetQuotesUseCase,
    private val insertQuoteUseCase: InsertQuoteUseCase
) : ViewModel() {

    private val _counterFlow = MutableStateFlow(initialCounter)

    val uiState = combine(_counterFlow, getQuotesUseCase()) { counter, quotes ->
        HomeState(
            counter = counter,
            quotesResult = quotes
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = HomeState(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L)
    )

    fun increment() {
//        _counterFlow.value += 2

        viewModelScope.launch {
            insertQuoteUseCase(
                author = "User ${_counterFlow.value}",
                content = "Content ${_counterFlow.value}"
            )
        }
    }

}
