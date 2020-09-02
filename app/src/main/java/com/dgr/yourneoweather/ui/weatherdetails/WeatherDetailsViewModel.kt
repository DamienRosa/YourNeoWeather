package com.dgr.yourneoweather.ui.weatherdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.functional.FailureBo
import com.dgr.domain.usecase.AddCityUseCase
import com.dgr.domain.usecase.GetWeatherUseCase
import com.dgr.yourneoweather.mapper.WeatherUIModelMapper
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherDetailsViewModel(
    private val addCityUseCase: AddCityUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val modelMapper: WeatherUIModelMapper
) : ViewModel() {

    private val _weatherDetails: MutableLiveData<WeatherUI> = MutableLiveData()
    var weatherDetails: LiveData<WeatherUI> = _weatherDetails

    fun saveCityWeather(weatherDetails: WeatherUI) {
        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase(modelMapper.toDomainModel(weatherDetails))
        }
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getWeatherUseCase(city)
            }.fold(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(weatherDomain: WeatherDomain) {
        val updatedWeatherModelUI = modelMapper.toUIModel(weatherDomain)
        _weatherDetails.postValue(updatedWeatherModelUI)
        saveCityWeather(updatedWeatherModelUI)
    }

    private fun handleFailure(failureBo: FailureBo) {

    }
}
