package com.creativijaya.basiccompose.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    @ColumnInfo(name =  "quote_id")
    val id: Long? = null,
    @ColumnInfo(name = "author")
    val author: String? = null,
    @ColumnInfo(name = "content")
    val content: String? = null,
    @ColumnInfo(name = "created_at")
    val createdAt: Date? = null,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date? = null
)
