package com.dgr.yourneoweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.usecase.GetCitiesUseCase
import com.dgr.yourneoweather.mapper.UIModelMapper
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val modelMapper: UIModelMapper
) : ViewModel() {

    private val mIsLoading = MutableLiveData<Boolean>()
    private val mIsError = MutableLiveData<Boolean>()
    private val mCityList = MutableLiveData<List<WeatherUI>>()

    fun isLoading(): LiveData<Boolean> = mIsLoading
    fun isError(): LiveData<Boolean> = mIsError
    fun cityList(): LiveData<List<WeatherUI>> = mCityList

    fun loadData() {
        viewModelScope.launch {
            mIsLoading.value = true
            val response = withContext(Dispatchers.IO) {
                getCitiesUseCase.invoke()
            }

            response.also {
                mIsLoading.value = false
                mIsError.value = it.isNullOrEmpty()
                mCityList.value = if (it.isNotEmpty()) modelMapper.toUIModel(it) else emptyList()
            }
        }
    }
}
