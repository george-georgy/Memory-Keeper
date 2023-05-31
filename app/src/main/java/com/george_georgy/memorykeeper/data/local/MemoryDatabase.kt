package com.george_georgy.memorykeeper.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.george_georgy.memorykeeper.model.Memory

@Database(entities = [Memory::class], version = 2)
abstract class MemoryDatabase : RoomDatabase(){

    abstract val memoryDao : MemoryDao

    companion object{
        // Volatile made visible to other threads.
        @Volatile
        var INSTANCE : MemoryDatabase? = null

        // Synchronized
        @Synchronized
        fun getDatabaseInstance(context : Context) : MemoryDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MemoryDatabase::class.java,
                    "memory.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE!!
        }
    }

}