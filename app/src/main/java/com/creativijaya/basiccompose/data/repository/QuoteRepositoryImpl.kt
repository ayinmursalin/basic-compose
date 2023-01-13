package com.creativijaya.basiccompose.data.repository

import com.creativijaya.basiccompose.data.local.db.daos.QuoteDao
import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val dao: QuoteDao
) : QuoteRepository {

    override fun getQuotes(): Flow<List<QuoteEntity>> {
        return dao.getQuotes()
    }

    override suspend fun insertQuote(quoteEntity: QuoteEntity): Long {
        return dao.insertQuote(quoteEntity)
    }

}
