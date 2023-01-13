package com.creativijaya.basiccompose.data.local.db

import androidx.room.TypeConverter
import java.util.*

class DbTypeConverters {

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun longToDate(time: Long?): Date? {
        return if (time == null) null else Date(time)
    }

}
