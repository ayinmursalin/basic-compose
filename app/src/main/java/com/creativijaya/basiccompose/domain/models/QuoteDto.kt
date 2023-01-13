package com.creativijaya.basiccompose.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class QuoteDto(
    val id: Long = 0,
    val author: String = "",
    val content: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) : Parcelable
