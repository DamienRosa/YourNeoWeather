package com.dgr.yourneoweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.functional.FailureBo
import com.dgr.domain.usecase.GetCitiesUseCase
import com.dgr.yourneoweather.mapper.WeatherUIModelMapper
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val modelMapper: WeatherUIModelMapper
) : ViewModel() {

    private val mIsLoading = MutableLiveData<Boolean>()
    private val mIsEmpty = MutableLiveData<Boolean>()
    private val mCityList = MutableLiveData<List<WeatherUI>>()

    fun isLoading(): LiveData<Boolean> = mIsLoading
    fun isEmpty(): LiveData<Boolean> = mIsEmpty
    fun cityList(): LiveData<List<WeatherUI>> = mCityList

    fun loadData() {
        viewModelScope.launch {
            mIsLoading.value = true
            withContext(Dispatchers.IO) {
                getCitiesUseCase()
            }.fold(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(weatherList: List<WeatherDomain>) {
        mIsLoading.postValue(false)
        mIsEmpty.postValue(weatherList.isNullOrEmpty())
        mCityList.postValue(modelMapper.toUIModel(weatherList))
    }


    private fun handleFailure(failure: FailureBo) {
        mIsLoading.value = false
        mIsEmpty.postValue(true)
        mCityList.postValue(emptyList())
    }
}
