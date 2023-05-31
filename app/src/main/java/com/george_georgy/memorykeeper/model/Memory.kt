package com.george_georgy.memorykeeper.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Memory(

    @PrimaryKey(autoGenerate = true)
    val memoryId : Int = 0,
    val memoryTitle : String,
    val memoryText : String
) : Parcelable