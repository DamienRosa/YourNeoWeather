package com.dgr.yourneoweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.CityWeather
import com.dgr.domain.usecase.GetCitiesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getCitiesUseCase: GetCitiesUseCase) : ViewModel() {

    private val mViewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = mViewState

    fun loadData() {
        viewModelScope.launch {
            getCitiesUseCase.invoke(false).also {
                if (it.isNotEmpty()) {
                    mViewState.value = ViewState(
                        isLoading = false,
                        isError = false,
                        citiesList = it
                    )
                } else {
                    mViewState.value = ViewState(
                        isLoading = false,
                        isError = true,
                        citiesList = emptyList()
                    )
                }
            }
        }
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val citiesList: List<CityWeather> = emptyList()
    )
}
