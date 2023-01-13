package com.creativijaya.basiccompose.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.creativijaya.basiccompose.data.local.db.daos.QuoteDao
import com.creativijaya.basiccompose.data.local.db.entities.QuoteEntity
import com.creativijaya.basiccompose.utils.AppConstant

@Database(
    entities = [
        QuoteEntity::class
    ],
    version = AppConstant.DB_VERSION
)
@TypeConverters(DbTypeConverters::class)
abstract class ComposeDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}
