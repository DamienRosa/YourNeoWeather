package com.dgr.yourneoweather.ui.searchcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.usecase.GetWeatherUseCase
import com.dgr.yourneoweather.mapper.WeatherUIModelMapper
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchCityViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val modelMapper: WeatherUIModelMapper
) : ViewModel() {

    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mIsLoading

    private val mWeatherData = MutableLiveData<WeatherUI>()
    val weatherDomain: LiveData<WeatherUI> = mWeatherData

    private val mIsError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = mIsError

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            mIsLoading.value = true
            val response = withContext(Dispatchers.IO) {
                getWeatherUseCase(cityName)
            }
            response.also {
                mIsLoading.value = false
                mIsError.value = it == null
                if (it != null) {
                    mWeatherData.value = checkNotNull(modelMapper.toUIModel(it))
                }
            }
        }
    }

    fun setModel(weatherUi: WeatherUI?) {
        mWeatherData.value = weatherUi
    }
}
