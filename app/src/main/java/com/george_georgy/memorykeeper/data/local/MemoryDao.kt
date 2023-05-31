package com.george_georgy.memorykeeper.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.george_georgy.memorykeeper.model.Memory

@Dao
interface MemoryDao {

    // insert and update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMemory(memory : Memory)

    @Delete
    suspend fun deleteMemory(memory : Memory)


    @Query("SELECT * FROM Memory ORDER BY memoryId ASC")
    fun getAllMemories() : LiveData<List<Memory>>

    @Query("SELECT * FROM Memory WHERE memoryTitle LIKE '%' ||:searchQuery||'%'")
    fun searchInMemoriesTitle(searchQuery: String) : LiveData<List<Memory>>
}