package com.widi.scan.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(history: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    suspend fun getAllHistory(): List<HistoryEntity>
}
