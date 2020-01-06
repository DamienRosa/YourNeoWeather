package com.dgr.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)