package com.dgr.yourneoweather.mapper

import android.annotation.SuppressLint
import com.dgr.domain.entity.WeatherDomain
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.model.WeatherUI
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.roundToInt

class UIModelMapper {

    fun toUIModel(domainList: List<WeatherDomain>): List<WeatherUI> =
        domainList.map {
            toUIModel(it)
        }

    fun toUIModel(weatherDomain: WeatherDomain): WeatherUI =
        WeatherUI(
            city = weatherDomain.city,
            country = weatherDomain.country,
            description = weatherDomain.description,
            windSpeed = weatherDomain.windSpeed,
            windDirection = weatherDomain.windDirection,
            temperature = weatherDomain.temperature.roundToInt(),
            humidity = weatherDomain.humidity,
            pressure = weatherDomain.pressure,
            visibility = weatherDomain.visibility,
            sunrise = weatherDomain.sunrise,
            sunset = weatherDomain.sunset,
            weatherIcon = weatherDomain.weatherIcon,
            lastUpdateDate = weatherDomain.lastUpdateDate
        )

    fun toDomainModel(uiModel: WeatherUI): WeatherDomain =
        WeatherDomain(
            city = uiModel.city,
            country = uiModel.country,
            description = uiModel.description,
            windSpeed = uiModel.windSpeed,
            windDirection = uiModel.windDirection,
            temperature = uiModel.temperature.toDouble(),
            humidity = uiModel.humidity,
            pressure = uiModel.pressure,
            visibility = uiModel.visibility,
            sunrise = uiModel.sunrise,
            sunset = uiModel.sunset,
            weatherIcon = uiModel.weatherIcon,
            lastUpdateDate = if (uiModel.lastUpdateDate.isNullOrEmpty()) todayDate() else uiModel.lastUpdateDate
        )

    @SuppressLint("SimpleDateFormat")
    private fun todayDate(): String {
        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return sdf.format(date)
    }

    private fun calcWindSpeed(windSpeed: Double): Int = (windSpeed * 3.6).roundToInt()

    private fun toCardinalDirection(windDirection: Int): String {
        val direction = listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val index: Int = ((windDirection / 45) % 8)
        return direction[index]
    }

    fun cardinalDirectionToImage(degrees: Int): Int =
        when (toCardinalDirection(degrees)) {
            "N" -> R.drawable.ic_direction_up
            "NW" -> R.drawable.ic_direction_up_left
            "W" -> R.drawable.ic_direction_left
            "SW" -> R.drawable.ic_direction_down_left
            "S" -> R.drawable.ic_direction_down
            "SE" -> R.drawable.ic_direction_down_right
            "E" -> R.drawable.ic_direction_right
            "NE" -> R.drawable.ic_direction_up_right
            else -> -1
        }

    fun iconIdToImage(weatherIcon: Int?): Int =
        when (weatherIcon) {
            804 -> R.drawable.ic_cloudy
            else -> R.drawable.ic_na
        }
}
