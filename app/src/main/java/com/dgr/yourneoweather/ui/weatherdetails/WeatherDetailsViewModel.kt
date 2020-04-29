package com.dgr.yourneoweather.ui.weatherdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.usecase.AddCityUseCase
import com.dgr.yourneoweather.mapper.WeatherUIModelMapper
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(
    private val addCityUseCase: AddCityUseCase,
    private val modelMapper: WeatherUIModelMapper) : ViewModel() {

    fun saveCityWeather(weatherDetails: WeatherUI) {
        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase.invoke(modelMapper.toDomainModel(weatherDetails))
        }
    }
}
