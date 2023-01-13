package com.creativijaya.basiccompose.ui.main.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("initialCounter") private val initialCounter: Int
) : ViewModel() {

    private val _counterFlow = MutableStateFlow(initialCounter)
    val counterFlow: StateFlow<Int>
        get() = _counterFlow.asStateFlow()

    fun increment() {
        _counterFlow.value += 2
    }

}
