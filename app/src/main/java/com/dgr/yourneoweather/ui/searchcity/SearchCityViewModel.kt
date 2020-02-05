package com.dgr.yourneoweather.ui.searchcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchCityViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading

    private val mWeatherData = MutableLiveData<WeatherDomain>()
    val weatherDomain: LiveData<WeatherDomain> = mWeatherData

    private val mIsError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = mIsError

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            mIsLoading.value = true
            val response = withContext(Dispatchers.IO) {
                getWeatherUseCase.invoke(cityName)
            }
            response.also {
                mIsLoading.value = false
                mIsError.value = it == null
                mWeatherData.value = it
            }
        }
    }

    fun setModel(weatherDomain: WeatherDomain?) {
        mWeatherData.value = weatherDomain
    }
}
