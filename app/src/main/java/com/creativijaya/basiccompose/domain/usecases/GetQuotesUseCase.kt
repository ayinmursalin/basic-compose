package com.creativijaya.basiccompose.domain.usecases

import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import com.creativijaya.basiccompose.data.repository.QuoteRepository
import com.creativijaya.basiccompose.domain.mappers.toDto
import com.creativijaya.basiccompose.domain.models.QuoteDto
import com.creativijaya.basiccompose.utils.ResultWrapper
import com.creativijaya.basiccompose.utils.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor (
    private val repository: QuoteRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<QuoteDto>>> {
        return repository.getQuotes().map {
            Success(it.map(QuoteEntity::toDto))
        }
    }
}
