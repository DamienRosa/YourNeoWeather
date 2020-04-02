package com.dgr.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val country: String,
    val description: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val visibility: Int,
    val sunset: Int,
    val sunrise: Int,
    val lastUpdateDate: String? = "",
    val weatherIcon: Int? = -1
)
