package com.creativijaya.basiccompose.domain.usecases

import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import com.creativijaya.basiccompose.data.repository.QuoteRepository
import java.util.Date
import javax.inject.Inject

class InsertQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(
        author: String,
        content: String
    ): Long {
        val entity = QuoteEntity(
            author = author,
            content = content,
            createdAt = Date(),
            updatedAt = Date()
        )

        return repository.insertQuote(entity)
    }
}
