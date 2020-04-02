package com.dgr.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dgr.data.BuildConfig
import com.dgr.data.db.dao.CityWeatherDao
import com.dgr.data.db.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class WeatherAppDataBase : RoomDatabase() {

    abstract fun getCityDao(): CityWeatherDao

    companion object {
        @Volatile
        private var instance: WeatherAppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context): WeatherAppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                WeatherAppDataBase::class.java,
                BuildConfig.DataBaseName
            ).build()
        }
    }
}
