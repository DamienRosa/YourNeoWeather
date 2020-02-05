package com.dgr.yourneoweather.ui.weatherdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.usecase.AddCityUseCase
import com.dgr.yourneoweather.common.model.WeatherUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(private val addCityUseCase: AddCityUseCase) : ViewModel() {

    fun saveCityWeather(weatherDetails: WeatherUI) {
        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase.invoke(weatherDetails.toCityModel())
        }
    }

    private fun WeatherUI.toCityModel(): WeatherDomain =
        WeatherDomain(
            city = this.city,
            country = this.country,
            description = this.description,
            windSpeed = this.windSpeed,
            windDirection = this.windDirection,
            temperature = this.temperature,
            humidity = this.humidity,
            pressure = this.pressure,
            visibility = this.visibility,
            sunrise = this.sunrise,
            sunset = this.sunset,
            weatherIcon = this.weatherIcon,
            lastUpdateDate = this.lastUpdateDate
        )
}
