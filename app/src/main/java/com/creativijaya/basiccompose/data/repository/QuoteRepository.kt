package com.creativijaya.basiccompose.data.repository

import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getQuotes(): Flow<List<QuoteEntity>>
    suspend fun insertQuote(quoteEntity: QuoteEntity): Long
}
