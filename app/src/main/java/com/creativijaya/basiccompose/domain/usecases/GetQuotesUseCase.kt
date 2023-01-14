package com.creativijaya.basiccompose.domain.usecases

import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import com.creativijaya.basiccompose.data.repository.QuoteRepository
import com.creativijaya.basiccompose.domain.mappers.toDto
import com.creativijaya.basiccompose.domain.models.QuoteDto
import com.creativijaya.basiccompose.utils.ResultWrapper
import com.creativijaya.basiccompose.utils.mapFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor (
    private val repository: QuoteRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<QuoteDto>>> {
        return repository.getQuotes().mapFlow {
            it.map(QuoteEntity::toDto)
        }
    }
}
