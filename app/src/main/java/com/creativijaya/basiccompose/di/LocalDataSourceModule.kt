package com.creativijaya.basiccompose.di

import android.content.Context
import androidx.room.Room
import com.creativijaya.basiccompose.data.local.db.ComposeDatabase
import com.creativijaya.basiccompose.data.local.db.daos.QuoteDao
import com.creativijaya.basiccompose.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ComposeDatabase {
        return Room.databaseBuilder(
            context,
            ComposeDatabase::class.java,
            AppConstant.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteDao(
        composeDatabase: ComposeDatabase
    ): QuoteDao {
        return composeDatabase.quoteDao()
    }

}
