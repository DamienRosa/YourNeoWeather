package com.dgr.yourneoweather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dgr.domain.usecase.GetCitiesUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val getCitiesUseCase: GetCitiesUseCase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(getCitiesUseCase) as T
    }
}