package com.creativijaya.basiccompose.ui.main.home

import android.util.Log
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

@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScaffold(
        uiState = uiState,
        onIncrementClick = viewModel::increment
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    uiState: HomeState,
    onIncrementClick: () -> Unit
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    if (uiState.counter > 0 && uiState.counter % 10 == 0) {
        LaunchedEffect(key1 = uiState.counter) {
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
                TextCounter(
                    counter = uiState.counter,
                    onIncrementClick = onIncrementClick
                )
                Spacer(modifier = Modifier.height(24.dp))
                QuoteList(quotesResult = uiState.quotesResult)
            }
        }
    }
}

@Composable
fun TextCounter(
    counter: Int = 0,
    onIncrementClick: () -> Unit = {}
) {
    Log.d("DEBUG_MAIN", "TextCounter - $counter")
    Text(text = "Value: $counter")
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onIncrementClick
    ) {
        Text(text = "Increment")
    }
}

@Composable
fun QuoteList(
    quotesResult: ResultWrapper<List<QuoteDto>> = Uninitialized
) {
    Log.d("DEBUG_MAIN", "QuoteList - $quotesResult")
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
