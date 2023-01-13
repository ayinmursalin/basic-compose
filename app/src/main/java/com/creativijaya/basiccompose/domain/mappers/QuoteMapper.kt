package com.creativijaya.basiccompose.domain.mappers

import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import com.creativijaya.basiccompose.domain.models.QuoteDto
import java.util.*

fun QuoteEntity.toDto() = QuoteDto(
    id = this.id ?: 0,
    author = this.author ?: "",
    content = this.content ?: "",
    createdAt = this.createdAt ?: Date(),
    updatedAt = this.updatedAt ?: Date()
)
