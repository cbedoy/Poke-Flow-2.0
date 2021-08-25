package com.poke.core.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity
@Parcelize
data class Poke(
    @PrimaryKey val number: Long,
    val name: String = "",
    val description: String = "",
    val secondaryDescription: String = "",
    val image: String = "",
    val svgImage: String = "",
    val color: String = "",
    val type: String = ""
) : Parcelable