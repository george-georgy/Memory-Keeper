package com.george_georgy.memorykeeper.data.repositories

import com.george_georgy.memorykeeper.data.local.MemoryDatabase
import com.george_georgy.memorykeeper.model.Memory

class MemoryRepository(
    memoryDatabase : MemoryDatabase
) {

    private val memoryDao = memoryDatabase.memoryDao


    // Database CRUD operation needs coroutines
    suspend fun upsertMemory(memory: Memory) = memoryDao.upsertMemory(memory)
    suspend fun deleteMemory(memory: Memory) = memoryDao.deleteMemory(memory)


    // No matter to use coroutine with select queries
    fun getMemories() = memoryDao.getAllMemories()
    fun searchMemory(searchQuery : String) = memoryDao.searchInMemoriesTitle(searchQuery)



}