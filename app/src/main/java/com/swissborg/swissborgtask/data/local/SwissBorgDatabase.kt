package com.swissborg.swissborgtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swissborg.swissborgtask.data.local.converters.RoomBigDecimalConverter
import com.swissborg.swissborgtask.data.local.entities.TickerEntity

@Database(
    entities = [TickerEntity::class],
    version = 1
)
@TypeConverters(
    RoomBigDecimalConverter::class
)
abstract class SwissBorgDatabase : RoomDatabase() {

    abstract val tickersDao: TickersDao
}
