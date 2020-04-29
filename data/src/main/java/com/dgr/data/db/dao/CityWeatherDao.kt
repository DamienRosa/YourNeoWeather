package com.dgr.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dgr.data.db.entity.CityEntity

@Dao
interface CityWeatherDao {
    @Query("SELECT * FROM CityEntity")
    fun getCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCity(city: CityEntity)
}
