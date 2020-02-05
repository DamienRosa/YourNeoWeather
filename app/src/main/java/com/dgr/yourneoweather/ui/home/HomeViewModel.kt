package com.dgr.yourneoweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgr.domain.entity.WeatherDomain
import com.dgr.domain.usecase.GetCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getCitiesUseCase: GetCitiesUseCase) : ViewModel() {

    private val mViewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = mViewState

    fun loadData() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getCitiesUseCase.invoke(false)
            }
            response.also {
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
        val citiesList: List<WeatherDomain> = emptyList()
    )
}
