package com.creativijaya.basiccompose.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.creativijaya.basiccompose.domain.models.QuoteDto
import com.creativijaya.basiccompose.utils.Error
import com.creativijaya.basiccompose.utils.ResultWrapper
import com.creativijaya.basiccompose.utils.Success
import com.creativijaya.basiccompose.utils.Uninitialized

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    if (uiState.counter > 0 && uiState.counter % 10 == 0) {
        LaunchedEffect(key1 = 1) {
            snackBarHostState.showSnackbar(
                "${uiState.counter}, can be divided by 10"
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextCounter(counter = uiState.counter)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        viewModel.increment()
                    }
                ) {
                    Text(text = "Increment")
                }
                Spacer(modifier = Modifier.height(24.dp))
                QuoteList(quotesResult = uiState.quotesResult)
            }
        }
    }
}

@Composable
fun TextCounter(
    counter: Int = 0
) {
    Text(text = "Value: $counter")
}

@Composable
fun QuoteList(
    quotesResult: ResultWrapper<List<QuoteDto>> = Uninitialized
) {
    when (quotesResult) {
        Uninitialized -> Surface {}
        is Error -> Text(text = "Error ${quotesResult.error.message}")
        is Success -> LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            quotesResult.data.map {
                item(key = it.id) {
                    Text("${it.author} - ${it.content}")
                }
            }
        }
    }
}
