package com.dgr.yourneoweather.ui.searchcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.City
import com.dgr.domain.entity.WeatherData
import com.dgr.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchCityViewModel(private val getWeatherUseCase: GetWeatherUseCase): ViewModel() {

    private val mViewState = MutableLiveData<ViewState>()
    val viewState : LiveData<ViewState> = mViewState

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            mViewState.value = ViewState(isLoading = true)
            val response = withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                getWeatherUseCase.invoke(City("", 0, cityName))
            }
            response.also {
                mViewState.value = ViewState(
                    isLoading = false,
                    isError = it == null,
                    weatherData = it
                )
            }
        }
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val weatherData: WeatherData? = null
    )
}
