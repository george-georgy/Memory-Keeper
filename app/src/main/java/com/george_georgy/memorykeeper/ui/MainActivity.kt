package com.george_georgy.memorykeeper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.george_georgy.memorykeeper.ui.viewModel.MemoryViewModel
import com.george_georgy.memorykeeper.ui.viewModel.MemoryViewModelProviderFactory
import com.george_georgy.memorykeeper.R
import com.george_georgy.memorykeeper.data.local.MemoryDatabase
import com.george_georgy.memorykeeper.data.repositories.MemoryRepository

class MainActivity : AppCompatActivity() {

    lateinit var memoryViewModel: MemoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val memoryRepository = MemoryRepository(
            (MemoryDatabase.getDatabaseInstance(this))
        )

        val viewModelProviderFactory =
            MemoryViewModelProviderFactory(
                application,
                memoryRepository
            )

        memoryViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(MemoryViewModel::class.java)
    }
}
