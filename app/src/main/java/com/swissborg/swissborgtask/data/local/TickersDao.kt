package com.swissborg.swissborgtask.data.local

import androidx.room.*
import com.swissborg.swissborgtask.data.local.entities.TickerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TickersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickers(tickersEntities: List<TickerEntity>)

    @Delete
    suspend fun deleteTicker(tickerEntity: TickerEntity)

    @Query(
        """
            UPDATE ${TickerEntity.TABLE_NAME}
            SET ${TickerEntity.COLUMN_FRIENDLY_NAME} = :friendlyName
            WHERE ${TickerEntity.COLUMN_SYMBOL} = :symbol
        """
    )
    suspend fun updateFriendlyName(symbol: String, friendlyName: String)

    @Query(
        """
            UPDATE ${TickerEntity.TABLE_NAME}
            SET ${TickerEntity.COLUMN_API_NAME} = :apiName
            WHERE ${TickerEntity.COLUMN_SYMBOL} = :symbol
        """
    )
    suspend fun updateApiName(symbol: String, apiName: String)

    @Query(
        """
            SELECT *
            FROM ${TickerEntity.TABLE_NAME}
            WHERE ${TickerEntity.COLUMN_FRIENDLY_NAME} LIKE :searchQuery 
            or ${TickerEntity.COLUMN_API_NAME} LIKE :searchQuery 
            or ${TickerEntity.COLUMN_SYMBOL} LIKE :searchQuery
        """
    )
    fun searchTickers(searchQuery: String): Flow<List<TickerEntity>>
}
