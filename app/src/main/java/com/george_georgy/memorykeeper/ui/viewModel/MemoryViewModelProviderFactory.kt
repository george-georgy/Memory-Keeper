package com.george_georgy.memorykeeper.ui.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.george_georgy.memorykeeper.data.repositories.MemoryRepository

class MemoryViewModelProviderFactory(
    val app: Application,
    private val memoryRepository: MemoryRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MemoryViewModel(app,memoryRepository) as T
    }


}