package com.george_georgy.memorykeeper.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.george_georgy.memorykeeper.model.Memory
import com.george_georgy.memorykeeper.data.repositories.MemoryRepository

import kotlinx.coroutines.launch

class MemoryViewModel(
    app:Application,
    private val memoryRepository: MemoryRepository
) : AndroidViewModel(app) {


    val memories = memoryRepository.getMemories()


    fun insertMemory(memory: Memory) = viewModelScope.launch {
        memoryRepository.upsertMemory(memory)
    }

    fun deleteMemory(memory: Memory) = viewModelScope.launch {
        memoryRepository.deleteMemory(memory)
    }

    fun searchMemories(searchQuery: String) =
        memoryRepository.searchMemory(searchQuery)

}



