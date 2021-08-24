package com.poke.core.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Stat(
    @PrimaryKey(autoGenerate = true)
    val key: Int = 0,
    val poke: Long,
    val name: String,
    val value: Int
) : Parcelable